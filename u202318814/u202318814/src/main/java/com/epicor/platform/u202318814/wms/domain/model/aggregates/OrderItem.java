package com.epicor.platform.u202318814.wms.domain.model.aggregates;

import com.epicor.platform.u202318814.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import com.epicor.platform.u202318814.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku;
import com.epicor.platform.u202318814.wms.domain.model.valueobjects.OrderEpicorSku;
import com.epicor.platform.u202318814.wms.domain.model.valueobjects.OrderItemStatus;
import com.epicor.platform.u202318814.wms.domain.model.valueobjects.RequestedQuantity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.query.Order;

import java.time.Instant;

@Entity
@Table(name = "order_items", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_id", "epicor_sku"})
})
public class OrderItem extends AuditableAbstractAggregateRoot<OrderItem> {

    @Getter
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "epicor_sku", nullable = false))
    private OrderEpicorSku epicorSku;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "requested_quantity", nullable = false))
    private RequestedQuantity requestedQuantity;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderItemStatus status;

    @Getter
    @Column(name = "ordered_at", nullable = false)
    private Instant orderedAt;

    // Constructor protegido para JPA
    protected OrderItem() {}

    // Constructor con reglas de negocio validadas en VOs
    public OrderItem(Long orderId, OrderEpicorSku epicorSku, RequestedQuantity requestedQuantity, Instant orderedAt, boolean isFullyAvailableInInventory) {
        if (orderId == null || orderId <= 0)
            throw new IllegalArgumentException("orderId must be greater than zero");
        if (orderedAt == null || orderedAt.isAfter(Instant.now()))
            throw new IllegalArgumentException("orderedAt cannot be in the future");

        this.orderId = orderId;
        this.epicorSku = epicorSku;
        this.requestedQuantity = requestedQuantity;
        this.orderedAt = orderedAt;
        this.status = isFullyAvailableInInventory
                ? OrderItemStatus.READY_FOR_DISPATCH
                : OrderItemStatus.WAITING_FOR_INVENTORY;
    }
}