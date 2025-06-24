package io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects;

import java.util.Arrays;

public enum MovieType {
    COMEDY, HORROR, FANTASY, ROMANCE, ACTION, ADVENTURE, THRILLER;

    public static MovieType fromName(String name) {
        return Arrays.stream(MovieType.values())
                .filter(mt -> mt.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie type: " + name));
    }
}
