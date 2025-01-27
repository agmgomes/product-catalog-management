package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.messaging.snsaws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@RequiredArgsConstructor
@Configuration
public class AwsSnsConfig {
    
    private final StaticCredentialsProvider awsCredentialsProvider;
    private final Region awsRegion;

    @Value("${aws.sns.topic.catalog.arn}")
    private String snsTopicArn;

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
        .region(awsRegion)
        .credentialsProvider(awsCredentialsProvider)
        .build();
    }

    @Bean
    public String snsTopicArn() {
        return snsTopicArn;
    }
}
