package com.agmgomes.productcatalogmanagement.application.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatalogEvent {
    
    private Long ownerId;
    private EntityType entityType;
    private String entityId;
    private CatalogAction action;

}
