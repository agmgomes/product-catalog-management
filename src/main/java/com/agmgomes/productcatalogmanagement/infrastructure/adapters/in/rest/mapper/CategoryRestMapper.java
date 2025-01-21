package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.request.CategoryRequestDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.response.CategoryResponseDto;

@Mapper(componentModel = "spring")
public interface CategoryRestMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category toDomain(CategoryRequestDto categoryDto);

    CategoryResponseDto toDto(Category category);

}
