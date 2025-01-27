package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.mapper;

import org.mapstruct.Mapper;

import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.catalog.response.CatalogResponseDto;

@Mapper(componentModel = "spring")
public interface CatalogRestMapper {
    CatalogResponseDto toDto(Catalog catalog);
}
