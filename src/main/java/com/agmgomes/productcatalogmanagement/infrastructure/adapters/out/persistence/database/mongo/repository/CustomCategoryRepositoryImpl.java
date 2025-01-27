package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.utils.IdWrapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {
    
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Long> findDistinctOwnerIds() {
    Aggregation aggregation = Aggregation.newAggregation(
        Aggregation.group("ownerId")
            .first("ownerId").as("ownerId")
    );

    AggregationResults<IdWrapper> results = mongoTemplate.aggregate(aggregation, "categories", IdWrapper.class);
    return results.getMappedResults().stream()
                  .map(IdWrapper::getOwnerId)
                  .collect(Collectors.toList());
}

}
