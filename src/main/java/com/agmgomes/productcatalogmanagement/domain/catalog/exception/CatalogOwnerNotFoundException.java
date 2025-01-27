package com.agmgomes.productcatalogmanagement.domain.catalog.exception;

public class CatalogOwnerNotFoundException extends RuntimeException {
    public CatalogOwnerNotFoundException(Long ownerId) {
        super("Catalog owner with id: " + ownerId + " not found.");
    }
}
