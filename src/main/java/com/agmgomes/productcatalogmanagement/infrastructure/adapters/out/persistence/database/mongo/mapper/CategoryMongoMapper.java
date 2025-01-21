package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.mapper;

import org.mapstruct.Mapper;

import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.CategoryCollection;

@Mapper(componentModel = "spring")
public interface CategoryMongoMapper {
    
    Category toDomain(CategoryCollection categoryCollection);
    CategoryCollection toCollection(Category category);
    
}
