package com.agmgomes.productcatalogmanagement.ports.out;

import java.util.Optional;

import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;

public interface CatalogStoragePort {
    void save(String key, Catalog catalog);
    Optional<Catalog> get(Long ownerId);
}
