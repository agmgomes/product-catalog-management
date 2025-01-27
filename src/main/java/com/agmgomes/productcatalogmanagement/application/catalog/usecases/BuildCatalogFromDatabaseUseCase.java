package com.agmgomes.productcatalogmanagement.application.catalog.usecases;

import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;

public interface BuildCatalogFromDatabaseUseCase {
    Catalog execute(Long ownerId);
}
