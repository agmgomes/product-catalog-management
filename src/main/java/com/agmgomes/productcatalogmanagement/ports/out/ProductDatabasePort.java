package com.agmgomes.productcatalogmanagement.ports.out;

import java.util.List;
import java.util.Optional;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface ProductDatabasePort {
    Product save(Product product);
    Optional<Product> findById(String productId);
    List<Product> findAll();
    void delete(String productId);

}
