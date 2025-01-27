package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.CategoryCollection;

import java.util.List;


@Repository
public interface CategoryMongoRepository extends MongoRepository<CategoryCollection, String>, CustomCategoryRepository{
    List<CategoryCollection> findByOwnerId(Long ownerId);
}
