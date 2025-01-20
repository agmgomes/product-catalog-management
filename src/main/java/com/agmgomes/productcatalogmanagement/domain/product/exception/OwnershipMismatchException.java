package com.agmgomes.productcatalogmanagement.domain.product.exception;

public class OwnershipMismatchException extends RuntimeException {
    
    public OwnershipMismatchException(Long productOwnerId, Long categoryOwnerId) {
        super("Product Owner id: " + productOwnerId + "does not equal Category Owner id: " + categoryOwnerId);
    }
}
