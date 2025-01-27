package com.agmgomes.productcatalogmanagement.application.catalog;

import org.springframework.stereotype.Service;

import com.agmgomes.productcatalogmanagement.application.catalog.usecases.BuildCatalogFromDatabaseUseCase;
import com.agmgomes.productcatalogmanagement.application.catalog.usecases.GetCatalogByOwnerIdUseCase;
import com.agmgomes.productcatalogmanagement.application.catalog.usecases.UpdateCatalogUseCase;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.ports.in.CatalogServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CatalogService implements CatalogServicePort {

    private final GetCatalogByOwnerIdUseCase getCatalogByOwnerIdUseCase;
    private final UpdateCatalogUseCase updateCatalogUseCase;
    private final BuildCatalogFromDatabaseUseCase buildCatalogFromDatabaseUseCase;

    @Override
    public Catalog getCatalog(Long ownerId) {
        return this.getCatalogByOwnerIdUseCase.execute(ownerId);
    }

    @Override
    public void saveCatalog(CatalogEvent catalogEvent) {
        this.updateCatalogUseCase.execute(catalogEvent);        
    }

    @Override
    public Catalog buildCatalog(Long ownerId) {
        return this.buildCatalogFromDatabaseUseCase.execute(ownerId);
    }
    
    
}
