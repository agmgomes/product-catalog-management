package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.awss3;

import java.io.IOException;
import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogProcessingException;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogStorageException;
import com.agmgomes.productcatalogmanagement.ports.out.CatalogStoragePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3CatalogStorageAdapter implements CatalogStoragePort {

    private final S3Client s3Client;
    private final String s3BucketName;
    private final ApplicationEventPublisher eventPublisher;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(String key, Catalog catalog) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3BucketName)
                .key(key)
                .contentType("application/json")
                .build();

        try {
            String catalogJson = objectMapper.writeValueAsString(catalog);
            s3Client.putObject(putObjectRequest, RequestBody.fromString(catalogJson));
        } catch (S3Exception e) {
            log.error("Failed to save catalog to S3. Error: {}", e.awsErrorDetails().errorMessage(), e);
            this.eventPublisher.publishEvent(new CatalogEvent(
                catalog.getOwnerId(),
                EntityType.CATALOG,
                null,
                CatalogAction.ERROR
            ));
            throw new CatalogStorageException("Failed to save catalog to S3.", e);

        } catch (JsonProcessingException e) {
            log.error("Error converting Catalog POJO to String.");
            throw new CatalogProcessingException("Failed to convert Catalog to String.", e);
        }
    }

    @Override
    public Optional<Catalog> get(Long ownerId) {
        String catalogKey = String.format("catalog-%d.json", ownerId);
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3BucketName)
                .key(catalogKey)
                .build();
        try {
            ResponseInputStream<GetObjectResponse> inputStream = s3Client.getObject(getObjectRequest);
            Catalog catalog = objectMapper.readValue(inputStream, Catalog.class);
            return Optional.of(catalog);
        } catch (S3Exception e) {
            if(e.awsErrorDetails().errorCode().equals("NoSuchKey")) {
                log.info("Catalog not found for id: {}. Returning empty.", ownerId);
                return Optional.empty();
            }
            log.error("Failed to get catalog from S3. Error: {}", e.awsErrorDetails().errorMessage(), e);
            throw new CatalogStorageException("Failed to get catalog from S3.", e);            
        } catch (IOException e) {
            log.error("Error converting JSON to Catalog Object.");
            throw new CatalogProcessingException("Failed to convert JSON to catalog", e);
        }
    }
    
}
