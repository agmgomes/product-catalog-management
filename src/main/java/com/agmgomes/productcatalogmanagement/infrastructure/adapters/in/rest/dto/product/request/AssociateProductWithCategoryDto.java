package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.request;

import jakarta.validation.constraints.NotBlank;

public record AssociateProductWithCategoryDto(
                @NotBlank String productId,
                @NotBlank String categoryId) {

}
