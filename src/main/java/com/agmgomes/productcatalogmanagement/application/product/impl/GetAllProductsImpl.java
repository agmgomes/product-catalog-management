package com.agmgomes.productcatalogmanagement.application.product.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.product.usecases.GetAllProductsUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductsListEmptyException;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetAllProductsImpl implements GetAllProductsUseCase {
    
    private final ProductDatabasePort productDatabasePort;

    @Override
    public List<Product> execute() {
        List<Product> allProducts = this.productDatabasePort.findAll();

        if(allProducts.isEmpty()) {
            throw new ProductsListEmptyException();
        }

        return allProducts;
    }
    
}
