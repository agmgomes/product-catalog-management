package com.agmgomes.productcatalogmanagement.application.catalog.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.catalog.usecases.BuildCatalogFromDatabaseUseCase;
import com.agmgomes.productcatalogmanagement.application.catalog.utils.CatalogBuilderUtil;
import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.domain.catalog.CategoryWithProducts;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.ports.in.CategoryServicePort;
import com.agmgomes.productcatalogmanagement.ports.in.ProductServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BuildCatalogFromDatabaseImpl implements BuildCatalogFromDatabaseUseCase {

    private final CategoryServicePort categoryServicePort;
    private final ProductServicePort productServicePort;

    @Override
    public Catalog execute(Long ownerId) {
        List<CategoryWithProducts> categoriesWithProducts = new ArrayList<>();
        List<Category> categories = this.categoryServicePort.getAllOwnerCategories(ownerId);
        List<Product> products = this.productServicePort.getAllOwnerProducts(ownerId);

        for (Category category : categories) {
            categoriesWithProducts.add(CatalogBuilderUtil.buildCategoryWithProducts(category, products));
        }

        return new Catalog(ownerId, categoriesWithProducts);
    }

}
