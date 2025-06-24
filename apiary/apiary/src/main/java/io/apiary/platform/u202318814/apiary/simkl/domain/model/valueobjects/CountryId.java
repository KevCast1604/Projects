package io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CountryId {
    @Column(name = "country_id", nullable = false, length = 2)
    private String id;

    protected CountryId() {}

    public CountryId(String id) {
        if (id == null || id.trim().isEmpty() || id.length() != 2)
            throw new IllegalArgumentException("Invalid countryId");
        this.id = id.toUpperCase();
    }

}
