package com.agmgomes.productcatalogmanagement.application.event.utils;

import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CatalogEventSerializer {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static String toJson(CatalogEvent catalogEvent) {
        try {
            return objectMapper.writeValueAsString(catalogEvent);
        } catch (JsonProcessingException e) {
            throw new CatalogProcessingException("Error writing Catalog Event into String.", e);
        }
    }
}
