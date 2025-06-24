package io.apiary.platform.u202318814.apiary.simkl.interfaces.rest.transform;

import io.apiary.platform.u202318814.apiary.simkl.domain.model.commands.CreateMovieCommand;
import io.apiary.platform.u202318814.apiary.simkl.interfaces.rest.resources.CreateMovieResource;

public class CreateMovieCommandFromResourceAssembler {
    public static CreateMovieCommand toCommand(CreateMovieResource resource) {
        return new CreateMovieCommand(
                resource.title(),
                resource.countryId(),
                resource.distributedId(),
                resource.movieType(),
                resource.sinopsis(),
                resource.imdbRating(),
                resource.budget(),
                resource.releaseAt()
        );
    }
}
