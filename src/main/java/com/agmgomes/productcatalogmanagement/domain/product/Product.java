package com.agmgomes.productcatalogmanagement.domain.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;

    private Long ownerId;
    private String categoryId;

    private String title;
    private String description;
    private BigDecimal price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void update(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
