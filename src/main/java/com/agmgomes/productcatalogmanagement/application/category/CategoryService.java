package com.agmgomes.productcatalogmanagement.application.category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agmgomes.productcatalogmanagement.application.category.usecases.DeleteCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.GetAllCategoriesByOwnerIdUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.GetAllCategoriesUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.GetAllOwnerIdsUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.GetCategoryByIdUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.RegisterCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.category.usecases.UpdateCategoryUseCase;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.ports.in.CategoryServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService implements CategoryServicePort {

    private final RegisterCategoryUseCase registerCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final GetAllCategoriesUseCase getAllCategoriesUseCase;
    private final GetAllCategoriesByOwnerIdUseCase getAllCategoriesByOwnerIdUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final GetAllOwnerIdsUseCase getAllOwnerIdsUseCase;

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
    public List<Category> getAllOwnerCategories(Long ownerId) {
        return this.getAllCategoriesByOwnerIdUseCase.execute(ownerId);
    }

    @Override
    public void deleteCategory(String categoryId) {
        this.deleteCategoryUseCase.execute(categoryId);
    }

    @Override
    public Category updateCategory(String categoryId, Category categoryData) {
        return this.updateCategoryUseCase.execute(categoryId, categoryData);
    }

    @Override
    public List<Long> getAllOwnerIds() {
        return this.getAllOwnerIdsUseCase.execute();
    }
    
}
