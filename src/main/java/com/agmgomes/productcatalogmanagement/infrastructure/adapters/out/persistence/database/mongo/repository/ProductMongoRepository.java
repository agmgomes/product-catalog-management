package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.ProductCollection;

public interface ProductMongoRepository extends MongoRepository<ProductCollection, String> {
    
}
