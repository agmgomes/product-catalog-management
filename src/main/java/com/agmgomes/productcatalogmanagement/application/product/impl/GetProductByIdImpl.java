package com.agmgomes.productcatalogmanagement.application.product.impl;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.product.usecases.GetProductByIdUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetProductByIdImpl implements GetProductByIdUseCase {
    
    private final ProductDatabasePort productDatabasePort;

    @Override
    public Product execute(String productId) {
        return this.productDatabasePort.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(productId));
    }
    
}
