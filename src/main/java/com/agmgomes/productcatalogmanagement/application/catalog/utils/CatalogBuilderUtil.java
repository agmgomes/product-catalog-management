package com.agmgomes.productcatalogmanagement.application.catalog.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.agmgomes.productcatalogmanagement.domain.catalog.CategoryWithProducts;
import com.agmgomes.productcatalogmanagement.domain.catalog.ProductForCatalog;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.product.Product;

public class CatalogBuilderUtil {
    
    public static CategoryWithProducts buildCategoryWithProducts(Category category, List<Product> products) {
        List<ProductForCatalog> categoryProducts = products.stream()
        .filter(product -> product.getCategoryId().equals(category.getId()))
        .map(CatalogBuilderUtil::buildProductForCatalog)
        .collect(Collectors.toList());

        return new CategoryWithProducts(
            category.getId(),
            category.getTitle(),
            category.getDescription(), 
            categoryProducts);
    }

    public static ProductForCatalog buildProductForCatalog(Product product) {
        return new ProductForCatalog(
            product.getId(),
            product.getTitle(),
            product.getDescription(),
            product.getPrice());
    }
}
