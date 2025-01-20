package com.agmgomes.productcatalogmanagement.application.product.impl;

import com.agmgomes.productcatalogmanagement.application.product.usecases.AssociateProductWithCategoryUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.domain.product.exception.OwnershipMismatchException;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssociateProductWithCategoryImpl implements AssociateProductWithCategoryUseCase {

    private final ProductDatabasePort productDatabasePort;
    private final CategoryDatabasePort categoryDatabasePort;

    @Override
    public void execute(String productId, String categoryId) {
        Product product = productDatabasePort.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Category category = categoryDatabasePort.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        validateOwnership(product, category);

        product.setCategoryId(category.getId());
        this.productDatabasePort.save(product);
    }

    private void validateOwnership(Product product, Category category) {
        Long productOwnerId = product.getOwnerId();
        Long categoryOwnerId = category.getOwnerId();

        if (!productOwnerId.equals(categoryOwnerId)) {
            throw new OwnershipMismatchException(productOwnerId, categoryOwnerId);
        }
    }
}
