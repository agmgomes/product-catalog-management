package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.request;

import java.math.BigDecimal;

public record ProductRequestDto(
        Long ownerId,
        String categoryId,
        String title,
        String description,
        BigDecimal price) {

}
