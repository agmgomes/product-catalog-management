package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.exception.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    Object error,
    String message,
    String path
) {
    
}
