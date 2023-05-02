package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Dto.PageSupport;
import com.hatmani.videostreamingsys.Utils.Converter;
import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.entity.Movie;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class CategorieService implements ICategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Flux<CategoryDto> getAllCategory() {
        return categorieRepository.findAll().map(Converter::categoryToDto);

    }
    @Override
    public Flux<Categorie> getAllCategory2() {

        return categorieRepository.findAll();

    }


    @Override
    public Mono<CategoryDto> getCategorie(String id) {
        return categorieRepository.findById(id).map(Converter::categoryToDto).onErrorReturn(null);

    }

    @Override
    public Mono<CategoryDto> saveCategorie(Mono<CategoryDto> dtoMono) {
        System.out.println("Service :saving categorie...");

       return dtoMono.map(Converter::DtoToCategorie)

               .flatMap(categorieRepository::insert)
               .map(Converter::categoryToDto);

    }

    @Override
    public Mono<CategoryDto> updateCategorie(Mono<CategoryDto> dtoMono, String id) {

        Mono<CategoryDto> categoryDtoMono = categorieRepository.findById(id)
                .flatMap(c -> dtoMono.map(Converter::DtoToCategorie)
                            .doOnNext(e -> e.setId(c.getId())))
                .flatMap(categorieRepository::insert)
                .map(Converter::categoryToDto);
        return categoryDtoMono;
    }

    @Override
    public Mono<PageSupport<CategoryDto>> getAllCategoriesByPage(int page, int size) {
        PageRequest pagere = PageRequest.of(page, size);
        System.out.println(pagere);

        return categorieRepository.findAllByIdNotNullOrderByIdAsc(pagere)
                .map(c->{return Converter.categoryToDto(c);})
                .collectList()
                .<PageSupport<CategoryDto>>flatMap(listpaged -> {
                    return categorieRepository.count().flatMap(c -> {
                                return Mono.just(
                                        new PageSupport<CategoryDto>(listpaged, pagere.getPageNumber(), pagere.getPageSize(), c)
                                );
                            }
                    );
                });

    }
    @Override
    public Mono<PageSupport<CategoryDto>> getAllCategoriescontainByPage(int page, int size,String keyword) {
        PageRequest pagere = PageRequest.of(page, size);
        System.out.println(pagere);

        return categorieRepository.findAllByNameContains(pagere,keyword)
                .map(c->{return Converter.categoryToDto(c);})
                .collectList()
                .<PageSupport<CategoryDto>>flatMap(listpaged -> {
                    return categorieRepository.findAllByNameContains(keyword).count().flatMap(c -> {
                                return Mono.just(
                                        new PageSupport<CategoryDto>(listpaged, pagere.getPageNumber(), pagere.getPageSize(), c)
                                );
                            }
                    );
                });

    }


    protected Mono<Movie> addMovieToCategorie(Mono<Movie> m, String idcateg) {

        System.out.println("add to categ");
      return  m
                .flatMap(movie -> {
                    return categorieRepository.findById(idcateg)
                            .flatMap(
                                    c->{
                                        List<Movie>lmv= new ArrayList<Movie>();
                                             if(c.getMovies()!=null)
                                             {lmv=   c.getMovies();}
                                        lmv.add(movie);
                                        c.setMovies(lmv);


                                    return categorieRepository.save(c);}
                                )

                            .map(categorie ->  movie);

                });

            /*    return m
                .flatMap(movie -> {
                   return categorieRepository.findById("63547b5008702616be34eedb")
                            .map(c->{
                                c.getMovies().add(movie);
                                return c;})
                            .flatMap(categorieRepository::save)
                            .map(categorie ->  movie);

                });*/


        //Ancien
     /*   Mono<Movie> movieMono = Mono.just(m)

                .map(movie -> {
                    movieDto.map(movieDto1 -> {
                        categorieRepository.findById(movieDto1.getId())
                                .flatMap(c -> {
                                    c.getMovies().add(m);
                                    return Mono.just(c);
                                })

                                .flatMap(categorieRepository::save).subscribe();
                         return movieDto1;
                    });
                    return movie;

                });
        return movieMono;

*/


    }


}
