package com.agmgomes.productcatalogmanagement.application.product.impl;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.product.usecases.RegisterProductUseCase;
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

    @Override
    public Product execute(Product productData) {
        String categoryId = productData.getCategoryId();
        
        this.categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        return this.productDatabasePort.save(productData);
    }

}
