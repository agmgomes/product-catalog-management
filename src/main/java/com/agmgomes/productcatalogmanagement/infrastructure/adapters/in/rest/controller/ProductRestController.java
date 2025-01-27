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

import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.request.AssociateProductWithCategoryDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.request.ProductRequestDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.product.response.ProductResponseDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.mapper.ProductRestMapper;
import com.agmgomes.productcatalogmanagement.ports.in.ProductServicePort;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/product")
public class ProductRestController {

    private final ProductRestMapper productRestMapper;
    private final ProductServicePort productServicePort;

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productDto) {

        Product newProduct = this.productServicePort.createProduct(this.productRestMapper.toDomain(productDto));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProduct.getId())
                .toUri();

        return ResponseEntity.created(location).body(this.productRestMapper.toDto(newProduct));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable("id") String productId) {

        Product foundProduct = this.productServicePort.getProductById(productId);

        return ResponseEntity.ok().body(this.productRestMapper.toDto(foundProduct));
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<ProductResponseDto>> getAllOwnerProducts(@PathVariable("id") Long ownerId) {
        List<Product> allProducts = this.productServicePort.getAllOwnerProducts(ownerId);
        List<ProductResponseDto> allProductsDto = allProducts.stream()
                .map(this.productRestMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(allProductsDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

        List<Product> allProducts = this.productServicePort.getAllProducts();
        List<ProductResponseDto> allProductsDto = allProducts.stream()
                .map(this.productRestMapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(allProductsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") String productId,
            @RequestBody ProductRequestDto productDto) {

        Product updatedProduct = this.productServicePort.updateProduct(productId,
                this.productRestMapper.toDomain(productDto));

        return ResponseEntity.ok().body(this.productRestMapper.toDto(updatedProduct));
    }

    @PutMapping("/associate")
    public ResponseEntity<Void> associateProductWithCategory(
            @Valid @RequestBody AssociateProductWithCategoryDto associateDto) {

        this.productServicePort.associateProductWithCategory(associateDto.productId(), associateDto.categoryId());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String productId) {

        this.productServicePort.deleteProduct(productId);

        return ResponseEntity.noContent().build();
    }

}
