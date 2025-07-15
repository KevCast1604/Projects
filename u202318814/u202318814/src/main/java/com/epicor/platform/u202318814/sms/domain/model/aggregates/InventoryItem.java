package com.epicor.platform.u202318814.sms.domain.model.aggregates;

import com.epicor.platform.u202318814.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.epicor.platform.u202318814.shared.domain.model.entities.AuditableModel;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.InventoryItemStatus;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.Quantity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.UUID;


@Entity
@Table(name = "inventory_items")
public class InventoryItem extends AuditableAbstractAggregateRoot<InventoryItem> {

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "epicor_sku", nullable = false, unique = true))
    private EpicorSku epicorSku;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private InventoryItemStatus status;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "minimum_quantity", nullable = false))
    private Quantity minimumQuantity;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "available_quantity", nullable = false))
    private Quantity availableQuantity;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "reserved_quantity"))
    private Quantity reservedQuantity;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "pending_supply_quantity"))
    private Quantity pendingSupplyQuantity;

    public InventoryItem(EpicorSku epicorSku, Quantity minimumQuantity, Quantity availableQuantity) {
        if (!availableQuantity.isAtLeastThreeTimes(minimumQuantity))
            throw new IllegalArgumentException("Available quantity must be at least 3x the minimum quantity");

        this.epicorSku = epicorSku;
        this.minimumQuantity = minimumQuantity;
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = Quantity.zero();
        this.pendingSupplyQuantity = Quantity.zero();
        this.status = InventoryItemStatus.WITH_STOCK;
    }


    public InventoryItem() {}
}
