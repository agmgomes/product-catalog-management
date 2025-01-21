package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.response;

import java.time.LocalDateTime;

public record CategoryResponseDto(
        String id,
        Long ownerId,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
