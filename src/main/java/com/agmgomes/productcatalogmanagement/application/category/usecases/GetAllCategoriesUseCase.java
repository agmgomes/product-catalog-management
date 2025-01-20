package com.agmgomes.productcatalogmanagement.application.category.usecases;

import java.util.List;

import com.agmgomes.productcatalogmanagement.domain.category.Category;

public interface GetAllCategoriesUseCase {
    List<Category> execute();
}
