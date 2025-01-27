package com.agmgomes.productcatalogmanagement.ports.in;

import java.util.List;

import com.agmgomes.productcatalogmanagement.domain.category.Category;

public interface CategoryServicePort {
    Category createCategory(Category categoryData);
    Category getCategoryById(String categoryId);
    List<Category> getAllCategories();
    List<Category> getAllOwnerCategories(Long ownerId);
    List<Long> getAllOwnerIds();
    Category updateCategory(String categoryId, Category categoryData);
    void deleteCategory(String categoryId);
}
