package io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DirectorId {
    @Column(name = "director_id", nullable = false)
    private Long id;

    protected DirectorId() {}

    public DirectorId(Long id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Invalid directorId");
        this.id = id;
    }
}
