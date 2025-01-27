package com.agmgomes.productcatalogmanagement.application.catalog.usecases;

import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;

public interface GetCatalogByOwnerIdUseCase {
    Catalog execute(Long ownerId);
}
