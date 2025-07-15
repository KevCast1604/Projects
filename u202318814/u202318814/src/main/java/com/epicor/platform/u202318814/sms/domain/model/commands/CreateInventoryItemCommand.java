package com.epicor.platform.u202318814.sms.domain.model.commands;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record CreateInventoryItemCommand(
        String epicorSku,
        Double minimumQuantity,
        Double availableQuantity
) {}