package com.agmgomes.productcatalogmanagement.ports.in;

import java.util.List;

import com.agmgomes.productcatalogmanagement.domain.product.Product;

public interface ProductServicePort {
    Product createProduct(Product productData);
    Product getProductById(String productId);
    List<Product> getAllProducts();
    List<Product> getAllOwnerProducts(Long ownerId);
    Product updateProduct(String productId, Product productData);
    void associateProductWithCategory(String productId, String categoryId);
    void deleteProduct(String productId);
}
