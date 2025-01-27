package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.catalog.response;

import java.util.List;

import com.agmgomes.productcatalogmanagement.domain.catalog.CategoryWithProducts;

public record CatalogResponseDto(
        Long ownerId,
        List<CategoryWithProducts> catalog) {
}
