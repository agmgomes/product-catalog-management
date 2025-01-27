package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponseDto(
        String id,
        Long ownerId,
        String categoryId,
        String title,
        String description,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
