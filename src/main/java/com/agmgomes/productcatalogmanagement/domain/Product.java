package com.agmgomes.productcatalogmanagement.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private String id;
    private Long ownerId;
    private Category category;
    private BigDecimal price;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
