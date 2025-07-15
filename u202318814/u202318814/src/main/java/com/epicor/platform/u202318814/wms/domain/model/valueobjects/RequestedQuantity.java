package com.epicor.platform.u202318814.wms.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class RequestedQuantity {
    private Double value;

    protected RequestedQuantity() {
        // JPA
    }

    public RequestedQuantity(Double value) {
        if (value == null)
            throw new IllegalArgumentException("RequestedQuantity must not be null");
        if (value <= 0)
            throw new IllegalArgumentException("RequestedQuantity must be greater than zero");
        this.value = value;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestedQuantity)) return false;
        RequestedQuantity that = (RequestedQuantity) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}