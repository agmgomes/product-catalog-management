package com.agmgomes.productcatalogmanagement.domain.category;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private String id;
    private Long ownerId;

    private String title;
    private String description;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public Category(Long ownerId, String title, String description) {
        this.id = null;
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }
}
