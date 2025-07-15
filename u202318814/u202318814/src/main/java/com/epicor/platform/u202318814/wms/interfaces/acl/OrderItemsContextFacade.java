package com.epicor.platform.u202318814.wms.interfaces.acl;

import com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku;
import com.epicor.platform.u202318814.sms.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsContextFacade {

    private final InventoryItemRepository inventoryItemRepository;

    public OrderItemsContextFacade(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }


    public boolean existsInventoryItemByEpicorSku(EpicorSku epicorSku) {
        return inventoryItemRepository.existsByEpicorSku(epicorSku);
    }
}