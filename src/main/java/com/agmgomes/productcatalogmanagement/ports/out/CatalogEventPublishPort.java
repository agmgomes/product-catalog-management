package com.agmgomes.productcatalogmanagement.ports.out;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;

public interface CatalogEventPublishPort {
    void publish(CatalogEvent catalogEvent);
}
