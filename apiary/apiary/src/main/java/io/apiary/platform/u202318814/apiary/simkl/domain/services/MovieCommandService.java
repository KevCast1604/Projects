package io.apiary.platform.u202318814.apiary.simkl.domain.services;

import io.apiary.platform.u202318814.apiary.simkl.domain.model.commands.CreateMovieCommand;
import io.apiary.platform.u202318814.apiary.simkl.domain.model.valueobjects.DirectorId;

public interface MovieCommandService {
    Long handle(CreateMovieCommand command, DirectorId directorId);
}
