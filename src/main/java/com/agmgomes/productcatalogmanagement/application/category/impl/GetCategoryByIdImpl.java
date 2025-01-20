package com.agmgomes.productcatalogmanagement.application.category.impl;

import com.agmgomes.productcatalogmanagement.application.category.usecases.GetCategoryByIdUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCategoryByIdImpl implements GetCategoryByIdUseCase {

    private final CategoryDatabasePort categoryDatabasePort;

    @Override
    public Category execute(String categoryId) {
        return this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

}
