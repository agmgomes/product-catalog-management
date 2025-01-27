package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.awss3;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.ports.in.CatalogServicePort;
import com.agmgomes.productcatalogmanagement.ports.in.CategoryServicePort;
import com.agmgomes.productcatalogmanagement.ports.out.BucketInitializerPort;
import com.agmgomes.productcatalogmanagement.ports.out.CatalogStoragePort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3BucketInitializerAdapter implements BucketInitializerPort {

    private final S3Client s3Client;
    private final String s3BucketName;
    private final CategoryServicePort categoryServicePort;
    private final CatalogServicePort catalogServicePort;
    private final CatalogStoragePort catalogStoragePort;

    @Override
    public void initializeBuckets() {
        log.info("Initializing and synchronizing S3 bucket with database.");

        List<Long> ownerIdsFromDatabase = this.categoryServicePort.getAllOwnerIds();
        log.info("Found {} owner IDs in the database.", ownerIdsFromDatabase.size());

        List<String> keysInS3 = listCatalogKeysFromS3();

        for (Long ownerId : ownerIdsFromDatabase) {
            String catalogKey = String.format("catalog-%d.json", ownerId);

            if (!keysInS3.contains(catalogKey)) {
                log.info("Catalog for ownerId: {} is missing in S3. Creating ...", ownerId);
            } else {
                log.info("Catalog for ownerId: {} exists in S3. Updating ...", ownerId);
            }

            Catalog catalog = this.catalogServicePort.buildCatalog(ownerId);
            this.catalogStoragePort.save(catalogKey, catalog);
        }

        for (String s3Key : keysInS3) {
            Long ownerIdFromS3 = extractOwnerIdFromKey(s3Key);
            if (!ownerIdsFromDatabase.contains(ownerIdFromS3)) {
                log.info("Catalog with S3 key: {} is no longer valid. Deleting ...", s3Key);
                deleteCatalogFromS3(s3Key);
            }
        }

        log.info("S3 Bucket initialization and synchronization completed.");
    }

    List<String> listCatalogKeysFromS3() {
        ListObjectsV2Request listRequest = ListObjectsV2Request.builder()
                .bucket(s3BucketName)
                .prefix("catalog-")
                .build();

        ListObjectsV2Response listResponse = s3Client.listObjectsV2(listRequest);

        return listResponse.contents().stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    private Long extractOwnerIdFromKey(String key) {
        try {
            String idPart = key.replace("catalog-", "").replace(".json", "");
            return Long.valueOf(idPart);
        } catch (NumberFormatException e) {
            log.error("Invalid catalog key format: {}. Skipping...", key);
            return null;
        }
    }

    private void deleteCatalogFromS3(String key) {
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(s3BucketName)
                .key(key)
                .build();

        try {
            s3Client.deleteObject(deleteRequest);
            log.info("Delete catalog with key: {} from S3.", key);
        } catch (S3Exception e) {
            log.error("Failed to delete catalog with key: {}. Error: {}", e.awsErrorDetails().errorMessage());
        }
    }
}
