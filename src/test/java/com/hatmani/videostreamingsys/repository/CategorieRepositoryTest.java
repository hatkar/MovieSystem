package com.hatmani.videostreamingsys.repository;

import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@ExtendWith(SpringExtension.class)

public class CategorieRepositoryTest {
    //@DataMongoTest :annotation utilisé pr les test qui se concentre uniquement sur les composant
    // MongoDb,par defaut il configurera :un mongoDb integrerer en memoire ,un MongoTemplate ,recherchera
    // les classes @Document et configurera les referentiels Spring Data MongoDbRepositories
//SpringExtension:elle permet l'integration de Spring TestContext with Junit 5
    @Autowired
    private CategorieRepository repository;

    // JUnit test for insert  method
    @DisplayName("JUnit test pour insert Categorie")
    @Test
    public void insert() {
        Categorie categorie = Categorie.builder()
                .name("Action")
                .description("Collection of Best Action Movie from world")
                .build();
        Mono<Categorie> categorieMono=repository.save(categorie);
       // Publisher<Categorie> setup = repository.deleteAll().thenMany(repository.save(categorie));
        StepVerifier.create(categorieMono)
              //  .expectNextCount(1)
                .assertNext(categorie1 -> assertNotNull(categorie1.getId()))

                .verifyComplete();
    }

    @DisplayName("JUnit test pour findById Categorie")
    @Test
    public void findById() {
        Categorie categorie = Categorie.builder()
                .name("Action")
                .description("Collection of Best Action Movie from world")
                .build();
        categorie = repository.save(categorie).block();
        Mono<Categorie> categorieMono = repository.findById(categorie.getId());
        StepVerifier.create(categorieMono)
                .assertNext(categorie1 -> {
                    assertEquals("Action", categorie1.getName());
                    assertEquals("Collection of Best Action Movie from world", categorie1.getDescription());
                    assertNotNull(categorie1.getId());
                })
                .expectComplete()
                .verify();
    }

    // JUnit test for findAll  method
    @DisplayName("JUnit test pour findAll Categorie")
    @Test
    public void findAll() {
        Categorie categorie = Categorie.builder()
                .name("Action")
                .description("Collection of Best Action Movie from world")
                .build();
        Categorie categorie2 = Categorie.builder()
                .name("Romance")
                .description("Collection of Best Romance Movie from world")
                .build();
        Categorie categorie3 = Categorie.builder()
                .name("Comedy")
                .description("Collection of Best Comedy Movie from world")
                .build();

                repository.deleteAll()
                        .thenMany(repository.saveAll(Flux.just(categorie,categorie2,categorie3)));
        Flux<Categorie> findAll=repository.findAll();

        StepVerifier.create(findAll)
                .expectNextCount(3)
                .verifyComplete();





    }
}
