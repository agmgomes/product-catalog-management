package com.agmgomes.productcatalogmanagement.application.product.utils;

import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.OwnershipMismatchException;

public class OwnerShipValidator {
    
    public static void validateOwnership(Product product, Category category) {
        Long productOwnerId = product.getOwnerId();
        Long categoryOwnerId = category.getOwnerId();

        if (!productOwnerId.equals(categoryOwnerId)) {
            throw new OwnershipMismatchException(productOwnerId, categoryOwnerId);
        }
    }
}
