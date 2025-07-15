package com.epicor.platform.u202318814.wms.domain.model.commands;

import java.time.Instant;

public record CreateOrderItemCommand(
        Long orderId,
        String epicorSku,         // Se valida que sea UUID válido más adelante
        Double requestedQuantity, // Se valida > 0
        Instant orderedAt         // No puede ser en el futuro
) {}