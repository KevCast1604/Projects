package com.epicor.platform.u202318814.sms.interfaces.rest.transform;

import com.epicor.platform.u202318814.sms.domain.model.commands.CreateInventoryItemCommand;
import com.epicor.platform.u202318814.sms.interfaces.rest.resources.CreateInventoryItemResource;

public class CreateInventoryItemFromResourceAssembler {

    public static CreateInventoryItemCommand toCommand(CreateInventoryItemResource resource) {
        return new CreateInventoryItemCommand(
                resource.epicorSku(),
                resource.minimumQuantity(),
                resource.availableQuantity()
        );
    }
}
