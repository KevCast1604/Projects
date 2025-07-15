package com.epicor.platform.u202318814.wms.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

import java.util.UUID;

@Embeddable
@Getter
public class OrderEpicorSku {
    private UUID value;

    protected OrderEpicorSku() {
        // JPA
    }

    public OrderEpicorSku(UUID value) {
        if (value == null)
            throw new IllegalArgumentException("Epicor SKU must not be null");

        this.value = value;
    }

    public UUID getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku)) return false;
        com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku that = (com.epicor.platform.u202318814.sms.domain.model.valueobjects.EpicorSku) o;
        return Objects.equals(value, that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}