package com.agmgomes.productcatalogmanagement.application.product.usecases;

public interface AssociateProductWithCategoryUseCase {
    void execute(String productId, String categoryId);
}
