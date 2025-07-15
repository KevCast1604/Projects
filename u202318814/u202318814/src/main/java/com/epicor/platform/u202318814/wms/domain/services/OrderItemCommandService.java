package com.epicor.platform.u202318814.wms.domain.services;

import com.epicor.platform.u202318814.wms.domain.model.aggregates.OrderItem;
import com.epicor.platform.u202318814.wms.domain.model.commands.CreateOrderItemCommand;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemCommandService {
    OrderItem handle(CreateOrderItemCommand command);
}