package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Utils.Converter;
import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.entity.Movie;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.internal.matchers.Any;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static sun.jvm.hotspot.runtime.VMOps.Verify;

@ExtendWith(SpringExtension.class)
class CategorieServiceTest {
    @Mock
    private CategorieRepository categorieRepository;
    @InjectMocks
    CategorieService categorieService;
    Categorie catego = Categorie.builder()
            .name("Action")
            .description("Collection of Best Action Movie from world")
            .build();
    Categorie categorie1 = Categorie.builder()
            .id("id1")
            .name("Action")
            .description("Collection of Best Action Movie from world")
            .build();
    Categorie categorie2 = Categorie.builder()
            .id("id2")
            .name("Romance")
            .description("Collection of Best Romance Movie from world")
            .build();
    Categorie categorie3 = Categorie.builder()
            .id("id3")
            .name("Comedy")
            .description("Collection of Best Comedy Movie from world")
            .build();

    @DisplayName("JUnit test pour findAllCategorie")
    @Test
    void getAllCategory() {
        //Flux<CategoryDto>

        Mockito.when(categorieRepository.findAll()).thenReturn(Flux.just(categorie1, categorie2, categorie3));
        Flux<CategoryDto> categoryDtoFlux = categorieService.getAllCategory();
        StepVerifier.create(categoryDtoFlux)
                .assertNext(categ1 -> assertEquals(Converter.categoryToDto(categorie1), categ1))
                .assertNext(categ2 -> assertEquals(Converter.categoryToDto(categorie2), categ2))
                .assertNext(categ3 -> assertEquals(Converter.categoryToDto(categorie3), categ3))
                .verifyComplete();

    }


    @Test
    void getCategorie() {
        //Mono<CategoryDto>
        Mockito.when(categorieRepository.findById("id1")).thenReturn(Mono.just(categorie1));
        Mono<CategoryDto> dtoMono = categorieService.getCategorie("id1");

        StepVerifier.create(dtoMono)
                .assertNext(categoryDto -> assertEquals(Converter.categoryToDto(categorie1), categoryDto))
                .verifyComplete();
    }

    @Test
    void saveCategorie() {
        Mockito.when(categorieRepository.insert((Categorie) any())).thenReturn(Mono.just(categorie1));
        Mono<CategoryDto> dtoMono = categorieService.saveCategorie(Mono.just(Converter.categoryToDto(catego)));

        StepVerifier.create(dtoMono)
                .assertNext(categoryDto -> {
                    assertEquals(Converter.categoryToDto(categorie1), categoryDto);
                })
                .verifyComplete();


    }

    @Test
    void updateCategorie() {
        //Mono<CategoryDto> updateCategorie(Mono<CategoryDto> dtoMono, String id)
        Mockito.when(categorieRepository.findById((String) any())).thenReturn(Mono.just(categorie1));
        Categorie catiterm = categorie1;
        catiterm.setId("idmod");

        //Categorie.builder().id(categorie1.getId()).name(categorie1.getName()).description(categorie1.getDescription()).movies(categorie1.getMovies()).build();
        Mockito.when(categorieRepository.insert((Categorie) any())).thenReturn(Mono.just(catiterm));
        Mono<CategoryDto> dtoMono = categorieService.updateCategorie(Mono.just(Converter.categoryToDto(categorie1)), "id2");

        StepVerifier.create(dtoMono)
                .assertNext(categoryDto -> {
                    assertEquals(catiterm.getName(), categoryDto.getName());
                    assertEquals(catiterm.getId(), "idmod");

                })
                .verifyComplete();

    }

    //addMovieToCategorie
    @Test
    void addMovieToCategorie() {

        Mockito.when(categorieRepository.findById((String) any())).thenReturn(Mono.just(categorie1));
        Mockito.when(categorieRepository.save(categorie1)).thenReturn(Mono.just(categorie1));
        Movie movie = Movie.builder().id("idmovie").moviename("moviename").description("desc").imageurl("kjlkj").fileurl("hkjhkjh").build();
        Mono<Movie> dtoMono = categorieService.addMovieToCategorie(Mono.just(movie), categorie1.getId());

        StepVerifier.create(dtoMono).assertNext(movie1 -> {
                    assertEquals(movie1, movie);
                    int taille2 = categorie1.getMovies() != null ? categorie1.getMovies().size() : 0;
                    assertTrue(taille2 == 1);
                }

        ).verifyComplete();

    }
}