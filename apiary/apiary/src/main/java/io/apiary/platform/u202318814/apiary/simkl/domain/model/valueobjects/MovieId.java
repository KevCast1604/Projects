package io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects;

import java.util.UUID;

public record MovieId(String movieId) {
    public MovieId() {
        this(UUID.randomUUID().toString());
    }
    public MovieId {
        if (movieId == null || movieId.isBlank()) {
            throw new IllegalArgumentException("Student code cannot be null or blank");
        }
        if (movieId.length() != 36) {
            throw new IllegalArgumentException("Student code must be 36 characters long");
        }
        if (!movieId.matches("[a-f0-9]{8}-([a-f0-9]{4}-){3}[a-f0-9]{12}")) {
            throw new IllegalArgumentException("Student code must be a valid UUID");
        }
    }
}