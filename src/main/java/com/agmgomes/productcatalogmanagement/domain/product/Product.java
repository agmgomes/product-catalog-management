package com.agmgomes.productcatalogmanagement.domain.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;

    private Long ownerId;
    private String categoryId;

    private String title;
    private String description;
    private BigDecimal price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(Long ownerId, String categoryId, String title, String description, BigDecimal price) {
        this.id = null;
        this.ownerId = ownerId;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }
}
