package io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DistributedId {
    @Column(name = "distributed_id", nullable = false)
    private Long id;

    protected DistributedId() {}

    public DistributedId(Long id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Invalid distributedId");
        this.id = id;
    }
}