package com.hatmani.videostreamingsys.repository;

import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.entity.Movie;
import com.sun.xml.internal.ws.binding.FeatureListUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie,String> {
    Mono<Movie> findMovieByMovienameContains(String name);
    Flux<Movie> findMovieByCategorieidContains(String categoryid);
    Flux<Movie> findMovieByCategorieidContainsAndMovienameContains(Pageable page,String categoryid,String Keyword);
    Flux<Movie> findMovieByCategorieidContainsAndMovienameContains(String categoryid,String Keyword);
    Flux<Movie> findMovieByMovienameContainsAndIdNotNull(Pageable page,String Keyword);
    Flux<Movie> findMovieByMovienameContainsAndIdNotNull(String Keyword);

    Flux<Movie> findMovieByCategorieidContainsAndReleaseyearBetween(Pageable page,String categoryid,String minyear,String maxyear);
    Flux<Movie> findMovieByCategorieidContainsAndReleaseyearBetween(String categoryid,String minyear,String maxyear);
    Flux<Movie> findMovieByMovienameContains(Pageable page,String keyword);
    Flux<Movie> findMovieByMovienameContainsAndCategorieidNotNull(String name);
    Flux<Movie> findAllByIdIsNotNull(Pageable page);

   // Flux<Movie> findAllByIdNotNullOrderByIdAsc(Pageable page);
    Flux<Movie> findMovieByCategorieidContains(Pageable page,String categoryid);
}
