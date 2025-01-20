package com.agmgomes.productcatalogmanagement.application.product.usecases;

import java.util.List;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface GetAllProductsUseCase {
    List<Product> execute();
}
