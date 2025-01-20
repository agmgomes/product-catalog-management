package com.agmgomes.productcatalogmanagement.application.product.impl;

import com.agmgomes.productcatalogmanagement.application.product.usecases.RegisterProductUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterProductImpl implements RegisterProductUseCase {
    
    private final ProductDatabasePort productDatabasePort;

    @Override
    public Product execute(Product productData) {
        return this.productDatabasePort.save(productData);    
    }
        
}
