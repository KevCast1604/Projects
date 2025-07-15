package com.epicor.platform.u202318814.wms.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class OrderId {

    private Long value;

    protected OrderId() {
        // JPA
    }

    public OrderId(Long value) {
        if (value == null)
            throw new IllegalArgumentException("OrderId must not be null");
        if (value <= 0)
            throw new IllegalArgumentException("OrderId must be greater than zero");
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderId)) return false;
        OrderId that = (OrderId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
