package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "categories")
@Getter
@Setter
public class CategoryCollection {
    @Id
    private String id;
    private Long ownerId;
    
    private String title;
    private String description;
    
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
        
}
