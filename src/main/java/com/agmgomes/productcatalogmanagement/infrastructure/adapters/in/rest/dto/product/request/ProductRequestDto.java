package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(
                @NotNull Long ownerId,
                @NotBlank String categoryId,
                @NotBlank String title,
                @NotBlank String description,
                @NotNull BigDecimal price) {

}
