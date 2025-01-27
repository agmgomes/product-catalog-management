package com.agmgomes.productcatalogmanagement.application.product.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.application.product.usecases.UpdateProductUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateProductImpl implements UpdateProductUseCase {

    private final ProductDatabasePort productDatabasePort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Product execute(String productId, Product productData) {
        Product product = this.productDatabasePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        
        product.update(productData.getTitle(), productData.getDescription(), productData.getPrice());

        Product updatedProduct = this.productDatabasePort.save(product);

        this.eventPublisher.publishEvent(new CatalogEvent(
            updatedProduct.getOwnerId(),
            EntityType.PRODUCT,
            updatedProduct.getId(),
            CatalogAction.UPDATED));

        return updatedProduct;
    }

}
