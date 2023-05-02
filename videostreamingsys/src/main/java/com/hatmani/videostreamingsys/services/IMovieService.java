package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Dto.MovieDtoRequest;
import com.hatmani.videostreamingsys.Dto.PageSupport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovieService {
    Mono<MovieDto> getMovie(String id);
    Flux<MovieDto> GetAllMovie();
    Flux<MovieDto> GetAllMovieOfCatgory(String categorieid);
    Mono<PageSupport<MovieDto>> getAllMovieOfCategoryByPage(int page, int size, String categoryid);
    Mono<PageSupport<MovieDto>> getAllMovieOfCategoryByPage(int page, int size, String idcategory, String keyword);
    Mono<PageSupport<MovieDto>> getAllMovieOfCategoryAndDateByPage(int page, int size, String idcategory, String minyear,String maxyear);
    Mono<PageSupport<MovieDto>> searchAllMovieByPage(int page, int size, String keyword);
    Mono<PageSupport<MovieDto>> searchAllMovieByPage(int page, int size);
    Mono<MovieDto> saveMovie(Mono<MovieDtoRequest> movieDto,String fileName, String movieNam);


    Mono<PageSupport<MovieDto>> getAllMovieContainsByPage(int page, int size, String keyword);
}
