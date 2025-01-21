package com.agmgomes.productcatalogmanagement.application.category.impl;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.RegisterCategoryUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RegisterCategoryImpl implements RegisterCategoryUseCase {

    private final CategoryDatabasePort categoryDatabasePort;
    
    @Override
    public Category execute(Category categoryData) {
        return this.categoryDatabasePort.save(categoryData);
    }
        
}
