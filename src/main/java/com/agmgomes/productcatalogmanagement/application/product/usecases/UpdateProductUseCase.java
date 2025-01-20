package com.agmgomes.productcatalogmanagement.application.product.usecases;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface UpdateProductUseCase {
    Product execute(String productId, Product productData);
}
