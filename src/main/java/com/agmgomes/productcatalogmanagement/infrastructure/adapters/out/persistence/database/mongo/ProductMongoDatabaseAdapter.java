package com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.collection.ProductCollection;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.mapper.ProductMongoMapper;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.out.persistence.database.mongo.repository.ProductMongoRepository;
import com.agmgomes.productcatalogmanagement.ports.out.ProductDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductMongoDatabaseAdapter implements ProductDatabasePort {

    private final ProductMongoRepository productMongoRepository;
    private final ProductMongoMapper productMongoMapper;

    @Override
    public void delete(String productId) {
        this.productMongoRepository.deleteById(productId);
    }

    @Override
    public List<Product> findAll() {
        List<ProductCollection> allProducts = this.productMongoRepository.findAll();

        return allProducts.stream()
                .map(this.productMongoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(String productId) {
        Optional<ProductCollection> foundProduct = this.productMongoRepository.findById(productId);
        return foundProduct.map(this.productMongoMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductCollection newProduct = this.productMongoMapper.toCollection(product);
        ProductCollection savedProduct = this.productMongoRepository.save(newProduct);

        return this.productMongoMapper.toDomain(savedProduct);
    }

}
