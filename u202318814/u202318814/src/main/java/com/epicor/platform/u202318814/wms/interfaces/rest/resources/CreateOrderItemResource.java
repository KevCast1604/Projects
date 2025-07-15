package com.epicor.platform.u202318814.wms.interfaces.rest.resources;

import java.time.Instant;

public record CreateOrderItemResource(
        Long orderId,
        String epicorSku,  // UUID en string
        Double requestedQuantity,
        Instant orderedAt
) {}