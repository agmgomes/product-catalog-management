package com.agmgomes.productcatalogmanagement.application.product.impl;

import com.agmgomes.productcatalogmanagement.application.product.usecases.DeleteProductUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteProductImpl implements DeleteProductUseCase {

    private final ProductDatabasePort productDatabasePort;

    @Override
    public void execute(String productId) {
        this.productDatabasePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        this.productDatabasePort.delete(productId);
    }

}
