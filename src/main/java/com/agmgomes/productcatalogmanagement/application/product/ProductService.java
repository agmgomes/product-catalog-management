package com.agmgomes.productcatalogmanagement.application.product;

import java.util.List;

import com.agmgomes.productcatalogmanagement.application.product.usecases.AssociateProductWithCategoryUseCase;
import com.agmgomes.productcatalogmanagement.application.product.usecases.DeleteProductUseCase;
import com.agmgomes.productcatalogmanagement.application.product.usecases.GetAllProductsUseCase;
import com.agmgomes.productcatalogmanagement.application.product.usecases.GetProductByIdUseCase;
import com.agmgomes.productcatalogmanagement.application.product.usecases.RegisterProductUseCase;
import com.agmgomes.productcatalogmanagement.application.product.usecases.UpdateProductUseCase;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.ports.in.ProductServicePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final AssociateProductWithCategoryUseCase associateProductWithCategoryUseCase;
    private final RegisterProductUseCase registerProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    
    @Override
    public void associateProductWithCategory(String productId, String categoryId) {
        this.associateProductWithCategoryUseCase.execute(productId, categoryId);
    }

    @Override
    public Product createProduct(Product productData) {
        return this.registerProductUseCase.execute(productData);
    }

    @Override
    public void deleteProduct(String productId) {
        this.deleteProductUseCase.execute(productId);        
    }
    
    @Override
    public List<Product> getAllProducts() {
        return this.getAllProductsUseCase.execute();
    }

    @Override
    public Product getProductById(String productId) {
        return this.getProductByIdUseCase.execute(productId);
    }

    @Override
    public Product updateProduct(String productId, Product productData) {
        return this.updateProductUseCase.execute(productId, productData);
    }
        
}
