package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.messaging.snsaws;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.utils.CatalogEventSerializer;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogPublishException;
import com.agmgomes.productcatalogmanagement.ports.out.CatalogEventPublishPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Slf4j
@RequiredArgsConstructor
@Component
public class SnsCatalogPublisherAdapter implements CatalogEventPublishPort {

    private final SnsClient snsClient;
    private final String snsTopicArn;

    @Override
    public void publish(CatalogEvent catalogEvent) {
        String jsonCatalogEvent = CatalogEventSerializer.toJson(catalogEvent);
        PublishRequest request = PublishRequest.builder()
                .message(jsonCatalogEvent)
                .topicArn(snsTopicArn)
                .build();
                
        try {
            PublishResponse result = snsClient.publish(request);
            log.info("Message publish to SNS. MessageId: {}, Status: {}",
                    result.messageId(), result.sdkHttpResponse().statusCode());
        } catch (SnsException e) {
            log.error(e.awsErrorDetails().errorMessage());
            throw new CatalogPublishException("Failed to publish catalog event to SNS", e);
        }

    }

}
