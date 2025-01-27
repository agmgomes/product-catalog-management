package com.agmgomes.productcatalogmanagement.ports.in;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;

public interface CatalogServicePort {
    void saveCatalog(CatalogEvent catalogEvent);
    Catalog getCatalog(Long ownerId);
    Catalog buildCatalog(Long ownerId);
}
