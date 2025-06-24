package io.apiary.platform.u202318814.apiary.simkl.domain.model.commands;

import java.time.LocalDate;

public record MovieResponse(
//        Long id,
        String title,
        String countryId,
        Long directorId,
        Long distributedId,
        String movieType,
        String sinopsis,
        Integer imdbRating,
        Float budget,
        LocalDate releaseAt
) {}
