package com.epicor.platform.u202318814.sms.domain.services;


import com.epicor.platform.u202318814.sms.domain.model.aggregates.InventoryItem;
import com.epicor.platform.u202318814.sms.domain.model.commands.CreateInventoryItemCommand;

import java.util.Optional;


public interface InventoryItemCommandService {
    InventoryItem handle(CreateInventoryItemCommand command);

}
