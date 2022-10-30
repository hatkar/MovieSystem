package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.MovieDto;
import reactor.core.publisher.Mono;

public interface IMovieService {
    Mono<MovieDto> getMovie(String id);

    Mono<MovieDto> saveMovie(Mono<MovieDto> movieDto);
}
