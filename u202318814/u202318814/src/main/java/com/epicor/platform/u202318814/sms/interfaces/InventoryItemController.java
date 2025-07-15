package com.epicor.platform.u202318814.sms.interfaces;

import com.epicor.platform.u202318814.sms.domain.model.commands.CreateInventoryItemCommand;
import com.epicor.platform.u202318814.sms.domain.services.InventoryItemCommandService;
import com.epicor.platform.u202318814.sms.interfaces.rest.resources.CreateInventoryItemResource;
import com.epicor.platform.u202318814.sms.interfaces.rest.resources.InventoryItemResource;
import com.epicor.platform.u202318814.sms.interfaces.rest.transform.CreateInventoryItemFromResourceAssembler;
import com.epicor.platform.u202318814.sms.interfaces.rest.transform.InventoryItemResourceFromEntityAssembler;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory-items")
public class InventoryItemController {

    private final InventoryItemCommandService inventoryItemCommandService;

    public InventoryItemController(InventoryItemCommandService inventoryItemCommandService) {
        this.inventoryItemCommandService = inventoryItemCommandService;
    }

    @PostMapping
    public ResponseEntity<InventoryItemResource> createInventoryItem(
            @Valid @RequestBody CreateInventoryItemResource resource) {

        // Transformar el recurso al comando
        CreateInventoryItemCommand command = CreateInventoryItemFromResourceAssembler.toCommand(resource);

        // Ejecutar el comando
        var createdInventoryItem = inventoryItemCommandService.handle(command);

        // Transformar a recurso de respuesta
        InventoryItemResource response = InventoryItemResourceFromEntityAssembler.toResourceFromEntity(createdInventoryItem);

        // Devolver 201 Created
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}