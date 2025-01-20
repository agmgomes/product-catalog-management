package com.agmgomes.productcatalogmanagement.application.product.impl;

import com.agmgomes.productcatalogmanagement.application.product.usecases.UpdateProductUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateProductImpl implements UpdateProductUseCase {

    private final ProductDatabasePort productDatabasePort;

    @Override
    public Product execute(String productId, Product productData) {
        Product product = this.productDatabasePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        
        product.update(productData.getTitle(), productData.getDescription(), productData.getPrice());

        return this.productDatabasePort.save(product);
    }

}
