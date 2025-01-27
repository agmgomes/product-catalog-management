package com.agmgomes.productcatalogmanagement.application.product.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.application.product.usecases.AssociateProductWithCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.product.utils.OwnerShipValidator;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AssociateProductWithCategoryImpl implements AssociateProductWithCategoryUseCase {

    private final ProductDatabasePort productDatabasePort;
    private final CategoryDatabasePort categoryDatabasePort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void execute(String productId, String categoryId) {
        Product product = productDatabasePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Category category = categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        OwnerShipValidator.validateOwnership(product, category);

        product.setCategoryId(category.getId());

        this.productDatabasePort.save(product);

        this.eventPublisher.publishEvent(new CatalogEvent(
                product.getOwnerId(),
                EntityType.PRODUCT,
                product.getId(),
                CatalogAction.CATEGORY_CHANGED));
    }

}
