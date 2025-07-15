package com.epicor.platform.u202318814.sms.application.internal.commandservices;

import com.epicor.platform.u202318814.sms.domain.model.aggregates.InventoryItem;
import com.epicor.platform.u202318814.sms.domain.model.commands.CreateInventoryItemCommand;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.Quantity;
import com.epicor.platform.u202318814.sms.infrastructure.persistence.jpa.repositories.InventoryItemRepository;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.InventoryItemStatus;
import com.epicor.platform.u202318814.sms.domain.services.InventoryItemCommandService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class InventoryItemCommandServiceImpl implements InventoryItemCommandService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryItemCommandServiceImpl(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository ;
    }

    @Override
    public InventoryItem handle(CreateInventoryItemCommand command) {
        UUID skuUUID;

        try {
            skuUUID = UUID.fromString(command.epicorSku());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("epicorSku must be a valid UUID");
        }

        EpicorSku epicorSku = new EpicorSku(skuUUID);

        // Cambiar a VO aquí, no UUID
        if (inventoryItemRepository.existsByEpicorSku(epicorSku)) {
            throw new IllegalArgumentException("InventoryItem with epicorSku " + command.epicorSku() + " already exists");
        }

        if (command.minimumQuantity() <= 0) {
            throw new IllegalArgumentException("minimumQuantity must be greater than zero");
        }
        if (command.availableQuantity() <= 0) {
            throw new IllegalArgumentException("availableQuantity must be greater than zero");
        }
        if (command.availableQuantity() < command.minimumQuantity() * 3) {
            throw new IllegalArgumentException("availableQuantity must be at least three times minimumQuantity");
        }

        Quantity minimumQuantity = new Quantity(command.minimumQuantity());
        Quantity availableQuantity = new Quantity(command.availableQuantity());

        InventoryItem inventoryItem = new InventoryItem(
                epicorSku,
                minimumQuantity,
                availableQuantity
        );

        return inventoryItemRepository.save(inventoryItem);
    }

}

