package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.CategoryCollection;

@Repository
public interface CategoryMongoRepository extends MongoRepository<CategoryCollection, String> {
    
}
