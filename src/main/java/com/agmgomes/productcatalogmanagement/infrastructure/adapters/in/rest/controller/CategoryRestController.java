package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.request.CategoryRequestDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.category.response.CategoryResponseDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.mapper.CategoryRestMapper;
import com.agmgomes.productcatalogmanagement.ports.in.CategoryServicePort;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/category")
public class CategoryRestController {

    private final CategoryRestMapper categoryRestMapper;
    private final CategoryServicePort categoryServicePort;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryDto) {

        Category newCategory = this.categoryServicePort.createCategory(this.categoryRestMapper.toDomain(categoryDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCategory.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoryRestMapper.toDto(newCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable("id") String categoryId) {

        Category foundCategory = this.categoryServicePort.getCategoryById(categoryId);

        return ResponseEntity.ok().body(categoryRestMapper.toDto(foundCategory));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<CategoryResponseDto>> getAllOwnerCategories(@PathVariable("id") Long ownerId) {
        List<Category> categories = this.categoryServicePort.getAllOwnerCategories(ownerId);
        List<CategoryResponseDto> categoriesDto = categories.stream()
                .map(this.categoryRestMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(categoriesDto);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {

        List<Category> categories = this.categoryServicePort.getAllCategories();
        List<CategoryResponseDto> categoriesDto = categories.stream()
                .map(categoryRestMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(categoriesDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") String categoryId,
            @RequestBody CategoryRequestDto categoryDto) {

        Category updatedCategory = this.categoryServicePort
                .updateCategory(categoryId, this.categoryRestMapper.toDomain(categoryDto));

        return ResponseEntity.ok().body(categoryRestMapper.toDto(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String categoryId) {

        this.categoryServicePort.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }

}
