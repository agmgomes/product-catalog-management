package com.agmgomes.productcatalogmanagement.application.category.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.RegisterCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RegisterCategoryImpl implements RegisterCategoryUseCase {

    private final CategoryDatabasePort categoryDatabasePort;
    private final ApplicationEventPublisher eventPublisher;
    
    @Override
    public Category execute(Category categoryData) {
        Category category = this.categoryDatabasePort.save(categoryData);

        this.eventPublisher.publishEvent(new CatalogEvent(
                categoryData.getOwnerId(),
                EntityType.CATEGORY,
                category.getId(),
                CatalogAction.CREATED));

        return category;
    }
        
}
