package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
public class ProductCollection {
    @Id
    private String id;
    private Long ownerId;
    private String categoryId;

    private String title;
    private String description;
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    
}
