package com.agmgomes.productcatalogmanagement.application.catalog.usecases;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;

public interface UpdateCatalogUseCase {
    void execute(CatalogEvent catalogEvent);
}
