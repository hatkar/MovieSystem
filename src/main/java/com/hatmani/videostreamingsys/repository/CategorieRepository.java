package com.hatmani.videostreamingsys.repository;

import com.hatmani.videostreamingsys.entity.Categorie;
import jdk.jfr.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CategorieRepository extends ReactiveMongoRepository<Categorie,String> {
     Mono<Categorie> insert(Categorie categorie);
     Flux<Categorie> findAll();
     Mono<Categorie> findById(String id);
}
