package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.CategoryCollection;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.mapper.CategoryMongoMapper;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository.CategoryMongoRepository;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CategoryMongoDatabaseAdapter implements CategoryDatabasePort {

    private final CategoryMongoRepository categoryMongoRepository;
    private final CategoryMongoMapper categoryMongoMapper;

    @Override
    public void delete(String categoryId) {
        this.categoryMongoRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> findAll() {
        List<CategoryCollection> allCategories = this.categoryMongoRepository.findAll();

        return allCategories.stream()
                .map(this.categoryMongoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findById(String categoryId) {
        Optional<CategoryCollection> foundCategory = this.categoryMongoRepository.findById(categoryId);

        return foundCategory.map(categoryMongoMapper::toDomain);

    }

    @Override
    public Category save(Category category) {
        CategoryCollection newCategory = this.categoryMongoMapper.toCollection(category);
        CategoryCollection savedCategory = this.categoryMongoRepository.save(newCategory);

        return this.categoryMongoMapper.toDomain(savedCategory);
    }

}
