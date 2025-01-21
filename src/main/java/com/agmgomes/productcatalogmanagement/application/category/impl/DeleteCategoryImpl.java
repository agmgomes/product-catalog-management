package com.agmgomes.productcatalogmanagement.application.category.impl;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.DeleteCategoryUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeleteCategoryImpl implements DeleteCategoryUseCase {
    
    private final CategoryDatabasePort categoryDatabasePort;

    @Override
    public void execute(String categoryId) {
        this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        
        this.categoryDatabasePort.delete(categoryId);
    }
    
}
