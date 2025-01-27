package com.agmgomes.productcatalogmanagement.application.category.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.UpdateCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateCategoryImpl implements UpdateCategoryUseCase {

    private final CategoryDatabasePort categoryDatabasePort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Category execute(String categoryId, Category categoryData) {
        Category category = this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        category.update(categoryData.getTitle(), categoryData.getDescription());

        Category updatedCategory = this.categoryDatabasePort.save(category);

        this.eventPublisher.publishEvent(new CatalogEvent(
                updatedCategory.getOwnerId(),
                EntityType.CATEGORY,
                updatedCategory.getId(),
                CatalogAction.UPDATED));

        return updatedCategory;
    }

}
