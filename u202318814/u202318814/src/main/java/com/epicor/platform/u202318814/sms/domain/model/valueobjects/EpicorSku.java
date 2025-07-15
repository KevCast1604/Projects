package com.epicor.platform.u202318814.sms.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

import java.util.UUID;
@Embeddable
public class EpicorSku {

    @Column(name = "epicor_sku", nullable = false, unique = true)
    private UUID value;

    protected EpicorSku() {
        // JPA
    }

    public EpicorSku(UUID value) {
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
        if (!(o instanceof EpicorSku)) return false;
        EpicorSku that = (EpicorSku) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}