package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.mapper;

import org.mapstruct.Mapper;

import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.ProductCollection;

@Mapper(componentModel = "spring")
public interface ProductMongoMapper {
    
    Product toDomain(ProductCollection productCollection);
    ProductCollection toCollection(Product product);
}
