package com.hatmani.videostreamingsys.repository;

import com.hatmani.videostreamingsys.entity.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends ReactiveMongoRepository<Movie,String> {
}
