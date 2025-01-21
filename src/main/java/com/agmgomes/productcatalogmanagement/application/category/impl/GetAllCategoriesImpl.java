package com.agmgomes.productcatalogmanagement.application.category.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.GetAllCategoriesUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoriesListEmptyException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetAllCategoriesImpl implements GetAllCategoriesUseCase {
    
    private final CategoryDatabasePort categoryDatabasePort;

    @Override
    public List<Category> execute() {
        List<Category> allCategories = this.categoryDatabasePort.findAll();
        
        if(allCategories.isEmpty()) {
            throw new CategoriesListEmptyException();
        }

        return allCategories;
    }

    
}
