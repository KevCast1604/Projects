package com.epicor.platform.u202318814.sms.interfaces.rest.transform;

import com.epicor.platform.u202318814.sms.domain.model.aggregates.InventoryItem;
import com.epicor.platform.u202318814.sms.interfaces.rest.resources.InventoryItemResource;

public class InventoryItemResourceFromEntityAssembler {

    public static InventoryItemResource toResourceFromEntity(InventoryItem entity) {
        return new InventoryItemResource(
                entity.getId(),
                entity.getEpicorSku().getValue().toString(),
                entity.getStatus().toString(),
                entity.getMinimumQuantity().getValue(),
                entity.getAvailableQuantity().getValue(),
                entity.getReservedQuantity().getValue(),
                entity.getPendingSupplyQuantity().getValue()
        );
    }
}