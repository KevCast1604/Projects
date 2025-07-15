package com.epicor.platform.u202318814.wms.interfaces.rest.transform;

import com.epicor.platform.u202318814.wms.domain.model.aggregates.OrderItem;
import com.epicor.platform.u202318814.wms.interfaces.rest.resources.OrderItemResource;

public class OrderItemResourceFromEntityAssembler {

    public static OrderItemResource toResourceFromEntity(OrderItem orderItem) {
        return new OrderItemResource(
                orderItem.getId(),
                orderItem.getOrderId(),
                orderItem.getEpicorSku().getValue().toString(),
                orderItem.getRequestedQuantity().getValue(),
                orderItem.getStatus().name(),
                orderItem.getOrderedAt()
        );
    }
}