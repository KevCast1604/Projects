package io.apiary.platform.u202318814.apiary.simkl.application.internal.commandservices;

import io.apiary.platform.u202318814.apiary.simkl.domain.model.aggregates.Movie;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.commands.CreateMovieCommand;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.commands.MovieResponse;
import io.apiary.platform.u202318814.apiary.simkl.domain.services.MovieCommandService;
import io.apiary.platform.u202318814.apiary.simkl.infrastructure.persistence.jpa.repositories.MovieRepository;
import org.springframework.stereotype.Service;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects.*;
@Service
public class MovieCommandServiceImpl implements MovieCommandService {

    private final MovieRepository movieRepository;

    public MovieCommandServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public Long handle(CreateMovieCommand command, DirectorId directorId) {
        // Convertir movieType a enum para la consulta
        var movieTypeEnum = MovieType.valueOf(command.movieType().toUpperCase());

        // Validar duplicado
        boolean exists = movieRepository.existsByTitleAndDirectorIdAndMovieType(
                command.title(),
                directorId,
                movieTypeEnum
        );

        if (exists) {
            throw new IllegalArgumentException("Movie with same title, director and type already exists.");
        }

        var movie = new Movie(command, directorId);
        try {
            movieRepository.save(movie);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving movie: " + e.getMessage());
        }

        return movie.getId();
    }
}
