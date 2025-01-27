package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.ProductCollection;
import java.util.List;


public interface ProductMongoRepository extends MongoRepository<ProductCollection, String> {
    List<ProductCollection> findByOwnerId(Long ownerId);
}
