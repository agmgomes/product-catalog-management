package com.agmgomes.productcatalogmanagement.application.catalog.impl;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.catalog.usecases.GetCatalogByOwnerIdUseCase;
import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogOwnerNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CatalogStoragePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetCatalogByOwnerId implements GetCatalogByOwnerIdUseCase {   

    private final CatalogStoragePort catalogStoragePort;

    @Override
    public Catalog execute(Long ownerId) {
        return this.catalogStoragePort.get(ownerId).orElseThrow(() -> new CatalogOwnerNotFoundException(ownerId));
    }

        
}
