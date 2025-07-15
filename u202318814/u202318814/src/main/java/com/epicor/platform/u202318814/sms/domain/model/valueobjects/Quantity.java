package com.epicor.platform.u202318814.sms.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Quantity {

    @Column(nullable = false)
    private Double value;

    protected Quantity() {
        // JPA
    }

    public Quantity(Double value) {
        if (value == null)
            throw new IllegalArgumentException("Quantity must not be null");
        if (value < 0)
            throw new IllegalArgumentException("Quantity must be greater or equal to zero");
        this.value = value;
    }


    public static Quantity from(Double value) {
        return new Quantity(value);
    }

    public Double getValue() {
        return value;
    }

    public boolean isAtLeastThreeTimes(Quantity other) {
        return this.value >= 3 * other.value;
    }

    public Quantity add(Quantity other) {
        return new Quantity(this.value + other.value);
    }

    public Quantity subtract(Quantity other) {
        double result = this.value - other.value;
        if (result < 0) throw new IllegalArgumentException("Resulting quantity cannot be negative");
        return new Quantity(result);
    }

    public static Quantity zero() {
        return new Quantity(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity quantity = (Quantity) o;
        return Objects.equals(value, quantity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
