package com.agmgomes.productcatalogmanagement.application.product.usecases;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface GetProductByIdUseCase {
    Product execute(String productId);
}
