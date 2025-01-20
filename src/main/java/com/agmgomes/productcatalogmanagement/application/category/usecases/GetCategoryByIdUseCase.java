package com.agmgomes.productcatalogmanagement.application.category.usecases;

import com.agmgomes.productcatalogmanagement.domain.category.Category;

public interface GetCategoryByIdUseCase {
    Category execute(String categoryId);
}
