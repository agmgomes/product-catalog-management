package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
        @NotNull Long ownerId,
        @NotBlank String title,
        @NotBlank String description) {

}
