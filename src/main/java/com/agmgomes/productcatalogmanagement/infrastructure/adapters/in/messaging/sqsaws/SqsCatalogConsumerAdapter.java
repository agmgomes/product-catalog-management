package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.messaging.sqsaws;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogProcessingException;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogQueueMessageConsumerException;
import com.agmgomes.productcatalogmanagement.ports.in.CatalogServicePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Slf4j
@RequiredArgsConstructor
@Component
public class SqsCatalogConsumerAdapter {

    private final SqsClient sqsClient;
    private final String sqsQueueUrl;
    private final CatalogServicePort catalogServicePort;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Scheduled(fixedDelay = 5000)
    public void pollMessages() {
        log.info(" {} - Polling Messages from queue", LocalDateTime.now().toString());
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .maxNumberOfMessages(10)
                .build();
        try {
            List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();

            for (Message message : messages) {
                processMessage(message);
                deleteMessage(message);
            }
        } catch (SqsException e) {
            log.error(e.awsErrorDetails().errorMessage());
            throw new CatalogQueueMessageConsumerException("Error consuming message.", e);
        }

    }

    public void processMessage(Message message) {
        String body = message.body();
        JsonNode jsonNode;
        log.info("Processing message: {}", message.messageId());
        try {
            jsonNode = mapper.readTree(body);
            if (jsonNode.has("Message")) {
                String catalogEventJson = jsonNode.get("Message").asText();

                CatalogEvent catalogEvent = mapper.readValue(catalogEventJson, CatalogEvent.class);
                log.info("ReceivedCatalogEventJSON: {}", catalogEventJson);

                this.catalogServicePort.saveCatalog(catalogEvent);

            } else {
                log.warn("Message body does not contain 'Message' field: {}", body);
            }

        } catch (JsonProcessingException e) {
            log.error("Error Processing", e);
            throw new CatalogProcessingException("Failed to read values from CatalogEvent.", e);
        }
    }

    public void deleteMessage(Message message) {
        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(sqsQueueUrl)
                .receiptHandle(message.receiptHandle())
                .build();

        DeleteMessageResponse response = sqsClient.deleteMessage(deleteRequest);
        log.info("Message deleted. MessageId: {}, Status: {}",
                message.messageId(), response.sdkHttpResponse().statusCode());
    }

}
