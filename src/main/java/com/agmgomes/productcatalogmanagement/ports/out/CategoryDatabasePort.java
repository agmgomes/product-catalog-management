package com.agmgomes.productcatalogmanagement.ports.out;

import java.util.List;
import java.util.Optional;

import com.agmgomes.productcatalogmanagement.domain.category.Category;

public interface CategoryDatabasePort {
    Category save(Category category);
    Optional<Category> findById(String categoryId);
    List<Category> findAll();
    List<Category> findAllByOwnerId(Long ownerId);
    List<Long> getAllOwnerIds();
    void delete(String categoryId);
}
