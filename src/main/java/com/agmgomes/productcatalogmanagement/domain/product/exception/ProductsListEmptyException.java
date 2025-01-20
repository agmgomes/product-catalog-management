package com.agmgomes.productcatalogmanagement.domain.product.exception;

public class ProductsListEmptyException extends RuntimeException {
    
    public ProductsListEmptyException() {
        super("List of products is empty.");
    }
}
