package com.agmgomes.productcatalogmanagement.application.category.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.agmgomes.productcatalogmanagement.application.category.usecases.GetAllOwnerIdsUseCase;
import com.agmgomes.productcatalogmanagement.ports.out.CategoryDatabasePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetAllOwnerIdsImpl implements GetAllOwnerIdsUseCase {
    
    private final CategoryDatabasePort categoryDatabasePort;
    
    @Override
    public List<Long> execute() {
        return this.categoryDatabasePort.getAllOwnerIds();
    }

}
