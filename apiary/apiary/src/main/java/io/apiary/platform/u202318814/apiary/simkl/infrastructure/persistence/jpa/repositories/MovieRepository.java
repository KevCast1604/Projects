package io.apiary.platform.u202318814.apiary.simkl.infrastructure.persistence.jpa.repositories;

import io.apiary.platform.u202318814.apiary.simkl.domain.model.aggregates.Movie;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects.DirectorId;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitleAndDirectorIdAndMovieType(String title, DirectorId directorId, MovieType type);
}
