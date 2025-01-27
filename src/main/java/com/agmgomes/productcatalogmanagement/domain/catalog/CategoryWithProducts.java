package com.agmgomes.productcatalogmanagement.domain.catalog;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryWithProducts {
    private String id;
    private String title;
    private String description;
    private List<ProductForCatalog> products;
}
