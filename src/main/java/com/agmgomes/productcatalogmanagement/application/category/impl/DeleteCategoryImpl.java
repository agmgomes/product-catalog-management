package com.agmgomes.productcatalogmanagement.application.category.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.DeleteCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeleteCategoryImpl implements DeleteCategoryUseCase {

    private final CategoryDatabasePort categoryDatabasePort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void execute(String categoryId) {
        Category category = this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        this.categoryDatabasePort.delete(categoryId);

        this.eventPublisher.publishEvent(new CatalogEvent(
                category.getOwnerId(),
                EntityType.CATEGORY,
                categoryId,
                CatalogAction.DELETED));

    }

}
