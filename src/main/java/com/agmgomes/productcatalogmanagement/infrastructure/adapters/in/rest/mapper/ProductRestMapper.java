package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.request.ProductRequestDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.response.ProductResponseDto;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toDomain(ProductRequestDto productDto);

    ProductResponseDto toDto(Product product);
    
}
