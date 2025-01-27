package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.ports.out.CatalogEventPublishPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class CatalogEventListener {
    
    private final CatalogEventPublishPort publishCatalogEventPort;

    @Async
    @EventListener
    public void handleCatalogEvent(CatalogEvent catalogEvent) {
        log.info("Processing event: Type: {} , Id: {}, Action: {}",
         catalogEvent.getEntityType().toString(),
         catalogEvent.getEntityId(),
         catalogEvent.getAction());

         publishCatalogEventPort.publish(catalogEvent);
    }
    
}
