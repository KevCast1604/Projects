package com.epicor.platform.u202318814.wms.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class OrderedAt {

    private LocalDateTime value;

    protected OrderedAt() {
        // JPA
    }

    public OrderedAt(LocalDateTime value) {
        if (value == null)
            throw new IllegalArgumentException("OrderedAt must not be null");
        if (value.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("OrderedAt cannot be in the future");
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedAt)) return false;
        OrderedAt that = (OrderedAt) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}