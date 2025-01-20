package com.agmgomes.productcatalogmanagement.application.product.usecases;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface RegisterProductUseCase {
    Product execute(Product productData);
}
