package com.agmgomes.productcatalogmanagement.infrastructure.adapters.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AwsCredentialsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.access.key.id}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;
    
    @Bean
    public StaticCredentialsProvider awsCredentialsProvider() {
        return StaticCredentialsProvider.create(
            AwsBasicCredentials.create(awsAccessKey, awsSecretKey)
        );
    }

    @Bean
    public Region awsRegion() {
        return Region.of(awsRegion);
    }

}
