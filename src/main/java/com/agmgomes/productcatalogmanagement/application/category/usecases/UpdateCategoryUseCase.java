package com.agmgomes.productcatalogmanagement.application.category.usecases;

import com.agmgomes.productcatalogmanagement.domain.category.Category;

public interface UpdateCategoryUseCase {
    Category execute(String categoryId, Category categoryData);
}
