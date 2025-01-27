package com.agmgomes.productcatalogmanagement.application.product.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.application.product.usecases.RegisterProductUseCase;
import com.agmgomes.productcatalogmanagement.application.product.utils.OwnerShipValidator;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RegisterProductImpl implements RegisterProductUseCase {

    private final ProductDatabasePort productDatabasePort;
    private final CategoryDatabasePort categoryDatabasePort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Product execute(Product productData) {
        String categoryId = productData.getCategoryId();
        
        Category category = this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        
        OwnerShipValidator.validateOwnership(productData, category);
        
        Product product = this.productDatabasePort.save(productData);

        this.eventPublisher.publishEvent(new CatalogEvent(
            product.getOwnerId(),
            EntityType.PRODUCT,
            product.getId(),
            CatalogAction.CREATED));

        return product;
    }

}
