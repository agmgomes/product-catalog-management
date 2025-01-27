package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.awss3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@RequiredArgsConstructor
@Configuration
public class AwsS3Config {
    
    private final StaticCredentialsProvider awsCredentialsProvider;
    private final Region awsRegion;

    @Value("${aws.s3.bucket.name}")
    private String s3BucketName;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
        .region(awsRegion)
        .credentialsProvider(awsCredentialsProvider)
        .build();
    }

    @Bean
    public String s3BucketName() {
        return s3BucketName;
    }
}
