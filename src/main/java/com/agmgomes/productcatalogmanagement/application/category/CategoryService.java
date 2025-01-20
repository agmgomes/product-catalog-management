package com.agmgomes.productcatalogmanagement.application.category;

import java.util.List;

import com.agmgomes.productcatalogmanagement.application.category.usecases.DeleteCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.GetAllCategoriesUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.GetCategoryByIdUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.RegisterCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.UpdateCategoryUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.ports.in.CategoryServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryService implements CategoryServicePort {

    private final RegisterCategoryUseCase registerCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @Override
    public Category createCategory(Category categoryData) {
        return this.registerCategoryUseCase.execute(categoryData);
    }

    @Override
    public Category getCategoryById(String categoryId) {
        return this.getCategoryByIdUseCase.execute(categoryId);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.getAllCategoriesUseCase.execute();
    }

    @Override
    public void deleteCategory(String categoryId) {
        this.deleteCategoryUseCase.execute(categoryId);
    }

    @Override
    public Category updateCategory(String categoryId, Category categoryData) {
        return this.updateCategoryUseCase.execute(categoryId, categoryData);
    }

}
