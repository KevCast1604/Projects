package com.epicor.platform.u202318814.wms.application.internal.commandservices;

import com.epicor.platform.u202318814.wms.domain.model.aggregates.OrderItem;
import com.epicor.platform.u202318814.wms.domain.model.commands.CreateOrderItemCommand;
import com.epicor.platform.u202318814.wms.domain.model.valueobjects.OrderEpicorSku;
import com.epicor.platform.u202318814.wms.domain.model.valueobjects.RequestedQuantity;
import com.epicor.platform.u202318814.wms.domain.services.OrderItemCommandService;
import com.epicor.platform.u202318814.wms.infrastructure.persistence.jpa.repositories.OrderItemRepository;
import com.epicor.platform.u202318814.wms.interfaces.acl.OrderItemsContextFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class OrderItemCommandServiceImpl implements OrderItemCommandService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemsContextFacade orderItemsContextFacade;

    public OrderItemCommandServiceImpl(OrderItemRepository orderItemRepository, OrderItemsContextFacade orderItemsContextFacade) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemsContextFacade = orderItemsContextFacade;
    }

    @Override
    @Transactional
    public OrderItem handle(CreateOrderItemCommand command) {

        if (command.orderId() == null || command.orderId() <= 0) {
            throw new IllegalArgumentException("orderId must be greater than zero");
        }

        OrderEpicorSku epicorSkuWms;
        try {
            epicorSkuWms = new OrderEpicorSku(UUID.fromString(command.epicorSku()));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("epicorSku must be a valid UUID");
        }

        // Convertir VO WMS a VO SMS usando FQN para llamar al ACL
        com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku epicorSkuSms =
                new com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku(epicorSkuWms.getValue());

        if (!orderItemsContextFacade.existsInventoryItemByEpicorSku(epicorSkuSms)) {
            throw new IllegalArgumentException("InventoryItem with epicorSku " + command.epicorSku() + " does not exist");
        }

        RequestedQuantity requestedQuantity = new RequestedQuantity(command.requestedQuantity());

        boolean exists = orderItemRepository.existsByOrderIdAndEpicorSku(command.orderId(), epicorSkuWms);
        if (exists) {
            throw new IllegalArgumentException("OrderItem with orderId " + command.orderId() + " and epicorSku " + command.epicorSku() + " already exists");
        }

        Instant now = Instant.now();
        if (command.orderedAt() == null || command.orderedAt().isAfter(now)) {
            throw new IllegalArgumentException("orderedAt cannot be in the future");
        }

        boolean isFullyAvailableInInventory = false; // Aquí podrías mejorar consultando qty real via ACL si quieres.

        OrderItem orderItem = new OrderItem(
                command.orderId(),
                epicorSkuWms,
                requestedQuantity,
                command.orderedAt(),
                isFullyAvailableInInventory
        );

        return orderItemRepository.save(orderItem);
    }
}
