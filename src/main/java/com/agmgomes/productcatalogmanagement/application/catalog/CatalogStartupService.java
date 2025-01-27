package com.agmgomes.productcatalogmanagement.application.catalog;

import org.springframework.stereotype.Service;

import com.agmgomes.productcatalogmanagement.ports.out.BucketInitializerPort;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CatalogStartupService {
    private final BucketInitializerPort bucketInitializerPort;

    @PostConstruct
    public void initialize() {
        this.bucketInitializerPort.initializeBuckets();
    }
}
