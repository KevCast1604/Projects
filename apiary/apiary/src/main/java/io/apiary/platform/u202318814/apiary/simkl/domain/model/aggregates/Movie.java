package io.apiary.platform.u202318814.apiary.simkl.domain.model.aggregates;


import io.apiary.platform.u202318814.apiary.simkl.domain.model.commands.CreateMovieCommand;
import jakarta.persistence.*;
import lombok.Getter;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects.*;
import io.apiary.platform.u202318814.apiary.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;


@Entity
@Table(name = "movies")
public class Movie extends AuditableAbstractAggregateRoot<Movie> {
    @Column(length = 100, nullable = false)
    private String title;

    @Embedded
    private CountryId countryId;

    @Embedded
    private DirectorId directorId;

    @Embedded
    private DistributedId distributedId;

    @Enumerated(EnumType.STRING)
    private MovieType movieType;

    @Column(length = 240, nullable = false)
    private String sinopsis;

    @Column(nullable = false)
    private Integer imdbRating;

    @Column(nullable = false)
    private Float budget;

    @Column(nullable = false)
    private LocalDate releaseAt;

    protected Movie() {}

    public Movie(String title, CountryId countryId, DirectorId directorId,
                 DistributedId distributedId, MovieType movieType, String sinopsis,
                 Integer imdbRating, Float budget, LocalDate releaseAt) {

        if (title == null || title.length() > 100)
            throw new IllegalArgumentException("Invalid title");
        if (sinopsis == null || sinopsis.length() > 240)
            throw new IllegalArgumentException("Invalid sinopsis");
        if (imdbRating == null || imdbRating < 1 || imdbRating > 10)
            throw new IllegalArgumentException("IMDB rating must be between 1 and 10");
        if (budget == null || budget <= 0)
            throw new IllegalArgumentException("Budget must be greater than 0");
        if (releaseAt == null)
            throw new IllegalArgumentException("Release date is required");

        this.title = title;
        this.countryId = countryId;
        this.directorId = directorId;
        this.distributedId = distributedId;
        this.movieType = movieType;
        this.sinopsis = sinopsis;
        this.imdbRating = imdbRating;
        this.budget = budget;
        this.releaseAt = releaseAt;
    }

    public Movie(CreateMovieCommand command, DirectorId directorId) {
        this(
                command.title(),
                new CountryId(command.countryId()),
                directorId,
                new DistributedId(command.distributedId()),
                MovieType.valueOf(command.movieType().toUpperCase()),
                command.sinopsis(),
                command.imdbRating(),
                command.budget(),
                command.releaseAt()
        );
    }
    public String getTitle() {
        return title;
    }

    public CountryId getCountryId() {
        return countryId;
    }

    public DirectorId getDirectorId() {
        return directorId;
    }

    public DistributedId getDistributedId() {
        return distributedId;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public Integer getImdbRating() {
        return imdbRating;
    }

    public Float getBudget() {
        return budget;
    }

    public LocalDate getReleaseAt() {
        return releaseAt;
    }
}
