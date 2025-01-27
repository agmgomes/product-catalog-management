package com.agmgomes.productcatalogmanagement.domain.catalog;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductForCatalog {
    private String id;
    private String title;
    private String description;
    private BigDecimal price;
}
