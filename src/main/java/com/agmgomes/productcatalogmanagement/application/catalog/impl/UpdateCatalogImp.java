package com.agmgomes.productcatalogmanagement.application.catalog.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.catalog.usecases.UpdateCatalogUseCase;
import com.agmgomes.productcatalogmanagement.application.catalog.utils.CatalogBuilderUtil;
import com.agmgomes.productcatalogmanagement.application.event.CatalogEvent;
import com.agmgomes.productcatalogmanagement.domain.catalog.Catalog;
import com.agmgomes.productcatalogmanagement.domain.catalog.CategoryWithProducts;
import com.agmgomes.productcatalogmanagement.domain.catalog.ProductForCatalog;
import com.agmgomes.productcatalogmanagement.domain.category.Category;
import com.agmgomes.productcatalogmanagement.domain.product.Product;
import com.agmgomes.productcatalogmanagement.ports.in.CategoryServicePort;
import com.agmgomes.productcatalogmanagement.ports.in.ProductServicePort;
import com.agmgomes.productcatalogmanagement.ports.out.CatalogStoragePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateCatalogImp implements UpdateCatalogUseCase {

    private final CategoryServicePort categoryServicePort;
    private final ProductServicePort productServicePort;
    private final CatalogStoragePort catalogStoragePort;

    @Override
    public void execute(CatalogEvent catalogEvent) {
        Catalog catalog = buildCatalog(catalogEvent);
        String keyCatalog = generateKey(catalog);
        this.catalogStoragePort.save(keyCatalog, catalog);
    }

    private Catalog buildCatalog(CatalogEvent catalogEvent) {
        Long ownerId = catalogEvent.getOwnerId();

        Catalog catalog = this.catalogStoragePort.get(ownerId).orElse(
                new Catalog(ownerId, new ArrayList<>()));

        switch (catalogEvent.getEntityType()) {
            case CATEGORY:
                handleCategoryEvent(catalog, catalogEvent);
                break;
            case PRODUCT:
                handleProductEvent(catalog, catalogEvent);
                break;

            case CATALOG:
                handleCatalogEvent(catalog, catalogEvent);
                break;
            default:
                break;
        }

        return catalog;
    }

    private void handleCategoryEvent(Catalog catalog, CatalogEvent catalogEvent) {
        switch (catalogEvent.getAction()) {
            case CREATED:
            case UPDATED:
                Category category = this.categoryServicePort.getCategoryById(catalogEvent.getEntityId());
                Optional<CategoryWithProducts> existingCategoryOpt = catalog.getCatalog().stream()
                        .filter(cat -> cat.getId().equals(category.getId()))
                        .findFirst();

                if (existingCategoryOpt.isPresent()) {
                    CategoryWithProducts existingCategory = existingCategoryOpt.get();
                    existingCategory.setTitle(category.getTitle());
                    existingCategory.setDescription(category.getDescription());
                } else {
                    catalog.getCatalog().add(CatalogBuilderUtil.buildCategoryWithProducts(category, new ArrayList<>()));
                }
                break;
            case DELETED:
                catalog.getCatalog().removeIf(cat -> cat.getId().equals(catalogEvent.getEntityId()));
            default:
                break;
        }
    }

    private void handleProductEvent(Catalog catalog, CatalogEvent catalogEvent) {
        switch (catalogEvent.getAction()) {
            case CREATED:
            case UPDATED:
                Product product = this.productServicePort.getProductById(catalogEvent.getEntityId());
                catalog.getCatalog().stream()
                        .filter(cat -> cat.getId().equals(product.getCategoryId()))
                        .findFirst()
                        .ifPresent(category -> {
                            Optional<ProductForCatalog> existingProductOpt = category.getProducts().stream()
                                    .filter(prod -> prod.getId().equals(product.getId()))
                                    .findFirst();

                            if (existingProductOpt.isPresent()) {
                                ProductForCatalog existingProduct = existingProductOpt.get();
                                existingProduct.setId(product.getId());
                                existingProduct.setTitle(product.getTitle());
                                existingProduct.setDescription(product.getDescription());
                                existingProduct.setPrice(product.getPrice());
                            } else {
                                category.getProducts().add(CatalogBuilderUtil.buildProductForCatalog(product));
                            }
                        });
                break;
            case CATEGORY_CHANGED:
                Product productWithChangedCategory = this.productServicePort.getProductById(catalogEvent.getEntityId());

                // Remove product from old category
                catalog.getCatalog().stream()
                        .filter(cat -> cat.getProducts().stream()
                                .anyMatch(prod -> prod.getId().equals(productWithChangedCategory.getId())))
                        .findFirst()
                        .ifPresent(category -> category.getProducts()
                                .removeIf(prod -> prod.getId().equals(productWithChangedCategory.getId())));

                // Add product to new category
                catalog.getCatalog().stream()
                        .filter(cat -> cat.getId().equals(productWithChangedCategory.getCategoryId()))
                        .findFirst()
                        .ifPresent(category -> category.getProducts()
                                .add(CatalogBuilderUtil.buildProductForCatalog(productWithChangedCategory)));
                break;
            case DELETED:
                catalog.getCatalog().stream()
                        .filter(cat -> cat.getProducts().stream()
                                .anyMatch(prod -> prod.getId().equals(catalogEvent.getEntityId())))
                        .findFirst()
                        .ifPresent(category -> category.getProducts()
                                .removeIf(prod -> prod.getId().equals(catalogEvent.getEntityId())));
            default:
                break;
        }
    }

    private void handleCatalogEvent(Catalog catalog, CatalogEvent catalogEvent) {
        switch (catalogEvent.getAction()) {
            case ERROR:
                List<CategoryWithProducts> categoriesWithProducts = new ArrayList<>();
                List<Category> categories = this.categoryServicePort.getAllOwnerCategories(catalog.getOwnerId());
                List<Product> products = this.productServicePort.getAllOwnerProducts(catalog.getOwnerId());

                for (Category category : categories) {
                    categoriesWithProducts.add(CatalogBuilderUtil.buildCategoryWithProducts(category, products));
                }
                catalog.setCatalog(categoriesWithProducts);
                break;

            default:
                break;
        }
    }

    private String generateKey(Catalog catalog) {
        return String.format("catalog-%d.json", catalog.getOwnerId());
    }

}
