package com.agmgomes.productcatalogmanagement.application.product.usecases;

import java.util.List;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface GetAllProductsByOwnerIdUseCase {
    List<Product> execute(Long ownerId);
}
