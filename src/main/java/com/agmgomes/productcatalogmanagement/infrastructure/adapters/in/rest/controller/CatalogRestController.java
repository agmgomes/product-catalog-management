package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.dto.catalog.response.CatalogResponseDto;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.mapper.CatalogRestMapper;
import com.agmgomes.productcatalogmanagement.ports.in.CatalogServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/catalog")
public class CatalogRestController {

    private final CatalogServicePort catalogServicePort;
    private final CatalogRestMapper catalogRestMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponseDto> getCatalog(@PathVariable("id") Long ownerId) {
        Catalog catalog = this.catalogServicePort.getCatalog(ownerId);

        return ResponseEntity.ok().body(this.catalogRestMapper.toDto(catalog));
    }
}
