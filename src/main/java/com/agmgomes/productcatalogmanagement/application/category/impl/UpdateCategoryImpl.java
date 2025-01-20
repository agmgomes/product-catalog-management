package com.agmgomes.productcatalogmanagement.application.category.impl;

import com.agmgomes.productcatalogmanagement.application.category.usecases.UpdateCategoryUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateCategoryImpl implements UpdateCategoryUseCase {

    private final CategoryDatabasePort categoryDatabasePort;

    @Override
    public Category execute(String categoryId, Category categoryData) {
        Category category = this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        category.update(categoryData.getTitle(), categoryData.getDescription());
        
        return this.categoryDatabasePort.save(category);
    }

}
