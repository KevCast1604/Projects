package com.epicor.platform.u202318814.wms.interfaces.rest.resources;

import java.time.Instant;

public record OrderItemResource(
        Long id,
        Long orderId,
        String epicorSku,
        Double requestedQuantity,
        String status,
        Instant orderedAt
) {}