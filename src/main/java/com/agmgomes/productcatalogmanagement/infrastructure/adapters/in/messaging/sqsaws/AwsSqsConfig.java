package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.messaging.sqsaws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@RequiredArgsConstructor
@Configuration
public class AwsSqsConfig {

    private final StaticCredentialsProvider awsCredentialsProvider;
    private final Region awsRegion;
    
    @Value("${aws.sqs.queue.url}")
    private String sqsQueueUrl;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder().region(awsRegion)
        .credentialsProvider(awsCredentialsProvider)
        .build();
    }

    @Bean
    public String sqsQueueUrl() {
        return sqsQueueUrl;
    }
}
