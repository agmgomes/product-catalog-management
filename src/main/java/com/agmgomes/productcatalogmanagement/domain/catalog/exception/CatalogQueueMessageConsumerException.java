package com.agmgomes.productcatalogmanagement.domain.catalog.exception;

public class CatalogQueueMessageConsumerException extends RuntimeException {
    public CatalogQueueMessageConsumerException(String message, Throwable cause) {
        super(message, cause);
    }
}
