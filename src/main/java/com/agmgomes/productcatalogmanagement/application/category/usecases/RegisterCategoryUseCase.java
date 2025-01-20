package com.agmgomes.productcatalogmanagement.application.category.usecases;

import com.agmgomes.productcatalogmanagement.domain.category.Category;

public interface RegisterCategoryUseCase {
    Category execute(Category categoryData);
}
