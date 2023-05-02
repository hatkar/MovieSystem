package com.hatmani.videostreamingsys.repository;

import com.hatmani.videostreamingsys.entity.Categorie;
import jdk.jfr.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CategorieRepository extends ReactiveSortingRepository<Categorie,String> {
     Mono<Categorie> insert(Categorie categorie);
     Flux<Categorie> findAll();
     Mono<Categorie> findById(String id);
     // Flux<Quote> findAllByIdNotNullOrderByIdAsc(final Pageable page);
     Flux<Categorie>findAllByIdNotNullOrderByIdAsc(Pageable page);
    Flux<Categorie>findAllByNameContains(Pageable page,String keyword);
    Flux<Categorie>findAllByNameContains(String keyword);

    // Page<Categorie> findAllByIdNotNullOrderByIdDesc(Pageable page);
     @Query(value = "{}", count = true)
     Long countWithAnnotation();
}
