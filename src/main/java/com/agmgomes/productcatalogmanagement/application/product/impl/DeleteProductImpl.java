package com.agmgomes.productcatalogmanagement.application.product.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.event.CatalogAction;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.application.event.EntityType;
import com.agmgomes.productcatalogmanagement.application.product.usecases.DeleteProductUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeleteProductImpl implements DeleteProductUseCase {

    private final ProductDatabasePort productDatabasePort;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void execute(String productId) {
        Product product = this.productDatabasePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        this.productDatabasePort.delete(productId);

        this.eventPublisher.publishEvent(new CatalogEvent(
            product.getOwnerId(),
            EntityType.PRODUCT,
            product.getId(),
            CatalogAction.DELETED));
    }

}
