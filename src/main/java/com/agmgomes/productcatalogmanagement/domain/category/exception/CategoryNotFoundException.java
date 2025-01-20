package com.agmgomes.productcatalogmanagement.domain.category.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String id) {
        super("Category with id: " + id + " not found.");
    }
}
