package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.request;

public record CategoryRequestDto(
                Long ownerId,
                String title,
                String description) {

}
