package com.epicor.platform.u202318814.wms.interfaces.rest;

import com.epicor.platform.u202318814.wms.application.internal.commandservices.OrderItemCommandServiceImpl;
import com.epicor.platform.u202318814.wms.domain.model.aggregates.OrderItem;
import com.epicor.platform.u202318814.wms.interfaces.rest.resources.CreateOrderItemResource;
import com.epicor.platform.u202318814.wms.interfaces.rest.resources.OrderItemResource;
import com.epicor.platform.u202318814.wms.interfaces.rest.transform.CreateOrderItemFromResourceAssembler;
import com.epicor.platform.u202318814.wms.interfaces.rest.transform.OrderItemResourceFromEntityAssembler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders/{orderId}/items")
public class OrderItemController {

    private final OrderItemCommandServiceImpl orderItemCommandService;

    public OrderItemController(OrderItemCommandServiceImpl orderItemCommandService) {
        this.orderItemCommandService = orderItemCommandService;
    }

    @PostMapping
    public ResponseEntity<OrderItemResource> createOrderItem(
            @PathVariable Long orderId,
            @Valid @RequestBody CreateOrderItemResource createOrderItemResource) {

        // Asegurar que el orderId del path y del resource coincidan
        if (!orderId.equals(createOrderItemResource.orderId())) {
            return ResponseEntity.badRequest().build();
        }

        var command = CreateOrderItemFromResourceAssembler.toCommandFromResource(createOrderItemResource);
        OrderItem orderItem = orderItemCommandService.handle(command);
        OrderItemResource responseResource = OrderItemResourceFromEntityAssembler.toResourceFromEntity(orderItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseResource);
    }
}