package com.agmgomes.productcatalogmanagement.domain.category.exception;

public class CategoriesListEmptyException extends RuntimeException {
    
    public CategoriesListEmptyException() {
        super("List of categories is empty.");
    }    
}
