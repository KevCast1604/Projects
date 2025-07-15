package com.epicor.platform.u202318814.wms.interfaces.rest.transform;

import com.epicor.platform.u202318814.wms.domain.model.commands.CreateOrderItemCommand;
import com.epicor.platform.u202318814.wms.interfaces.rest.resources.CreateOrderItemResource;

public class CreateOrderItemFromResourceAssembler {

    public static CreateOrderItemCommand toCommandFromResource(CreateOrderItemResource resource) {
        return new CreateOrderItemCommand(
                resource.orderId(),
                resource.epicorSku(),
                resource.requestedQuantity(),
                resource.orderedAt()
        );
    }
}