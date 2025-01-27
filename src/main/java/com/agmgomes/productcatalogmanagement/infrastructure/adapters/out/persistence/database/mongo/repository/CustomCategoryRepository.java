package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository;

import java.util.List;

public interface CustomCategoryRepository {
    List<Long> findDistinctOwnerIds();
}
