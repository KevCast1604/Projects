package com.epicor.platform.u202318814.sms.interfaces.rest.resources;

public record InventoryItemResource(
        Long id,
        String epicorSku,
        String status,
        Double minimumQuantity,
        Double availableQuantity,
        Double reservedQuantity,
        Double pendingSupplyQuantity
) { }