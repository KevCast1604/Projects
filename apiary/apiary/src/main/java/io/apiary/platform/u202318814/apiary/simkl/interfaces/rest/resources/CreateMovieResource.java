package io.apiary.platform.u202318814.apiary.simkl.interfaces.rest.resources;


import java.time.LocalDate;

public record CreateMovieResource(String title, String countryId, Long distributedId,
                                  String movieType,
                                  String sinopsis,
                                  Integer imdbRating,
                                  Float budget,
                                  LocalDate releaseAt) {}
