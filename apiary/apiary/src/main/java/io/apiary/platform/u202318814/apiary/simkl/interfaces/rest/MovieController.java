package io.apiary.platform.u202318814.apiary.simkl.interfaces.rest;

import io.apiary.platform.u202318814.apiary.simkl.domain.model.commands.CreateMovieCommand;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects.DirectorId;
import io.apiary.platform.u202318814.apiary.simkl.domain.services.MovieCommandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/directors")
public class MovieController {

    private final MovieCommandService movieCommandService;

    public MovieController(MovieCommandService movieCommandService) {
        this.movieCommandService = movieCommandService;
    }

    @PostMapping("/{directorId}/movies")
    public ResponseEntity<?> createMovie(
            @PathVariable Long directorId,
            @RequestBody CreateMovieCommand request
    ) {
        try {
            var command = new CreateMovieCommand(
                    request.title(),
                    request.countryId(), // String (ej: "US", "PE", etc.)
                    request.distributedId(),
                    request.movieType(),
                    request.sinopsis(),
                    request.imdbRating(),
                    request.budget(),
                    request.releaseAt()
            );

            var movieId = movieCommandService.handle(command, new DirectorId(directorId));

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("id", movieId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error: " + e.getMessage()));
        }
    }

}
