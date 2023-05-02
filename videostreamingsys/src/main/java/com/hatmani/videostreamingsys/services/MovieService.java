package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Dto.MovieDtoRequest;
import com.hatmani.videostreamingsys.Dto.PageSupport;
import com.hatmani.videostreamingsys.Utils.Converter;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import com.hatmani.videostreamingsys.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

//===>Difference entre map et faltMap
//!!!!!!flatmap doit etre utilise dans le cas des operation et bloc de code non-blokan!!!
//exemple appelle de de reactiverepository
//!!!!!!Map est utiliser dans le cas ou on veut faire une transformation d'un objet/data dans le temp est fixe"synchrone"!!!!
//exemple UPERCASE
@Service
//@RequiredArgsConstructor
public class MovieService implements IMovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private CategorieService categoryService;

    @Override
    public Mono<MovieDto> getMovie(String id) {
        return movieRepository.findById(id).map(Converter::MovieToDto).onErrorReturn(null);
    }
    @Override
    public Flux<MovieDto> GetAllMovie() {
        return movieRepository.findAll().map(Converter::MovieToDto).onErrorReturn(null);
    }

    @Override
    public Flux<MovieDto> GetAllMovieOfCatgory(String categorieid) {
        System.out.println("SERVICE GETTING MOVIE BY CATEGORIE");
        return movieRepository.findMovieByCategorieidContains(categorieid).map(Converter::MovieToDto).onErrorReturn(null);
    }

    @Override
    public Mono<PageSupport<MovieDto>> getAllMovieOfCategoryByPage(int page, int size, String categoryid) {

        PageRequest pagere = PageRequest.of(page, size);
       // movieRepository.findMovieByCategorieidContains(pagere,categoryid).subscribe(m-> System.out.println(m));
        return movieRepository.findMovieByCategorieidContains(pagere,categoryid)
                .map(m->{return  Converter.MovieToDto(m);})
                .collectList()
                .<PageSupport<MovieDto>>flatMap(listpaged->{
                    return movieRepository.count().flatMap(c->{
                        return  Mono.just(
                                new PageSupport<MovieDto>(listpaged,pagere.getPageNumber(),pagere.getPageSize(),c)
                        );
                    });
                });
    }

    @Override
    public Mono<PageSupport<MovieDto>> getAllMovieOfCategoryByPage(int page, int size, String idcategory, String keyword) {
        PageRequest pagere = PageRequest.of(page, size);

        return movieRepository.findMovieByCategorieidContainsAndMovienameContains(pagere,idcategory,keyword)
                .map(m->{return  Converter.MovieToDto(m);})
                .collectList()
                .<PageSupport<MovieDto>>flatMap(listpaged->{
                    return movieRepository.findMovieByCategorieidContainsAndMovienameContains(idcategory,keyword).count().flatMap(c->{
                        return  Mono.just(
                                new PageSupport<MovieDto>(listpaged,pagere.getPageNumber(),pagere.getPageSize(),c)
                        );
                    });
                });
    }

    @Override
    public Mono<PageSupport<MovieDto>> getAllMovieOfCategoryAndDateByPage(int page, int size, String idcategory, String minyear, String maxyear) {
        PageRequest pagere = PageRequest.of(page, size);
//findMovieByCategorieidLikeAndReleaseyearBetween
        return movieRepository.findMovieByCategorieidContainsAndReleaseyearBetween(pagere,idcategory,minyear,maxyear)
                .map(m->{return  Converter.MovieToDto(m);})
                .collectList()
                .<PageSupport<MovieDto>>flatMap(listpaged->{
                    return movieRepository.findMovieByCategorieidContainsAndReleaseyearBetween(idcategory,minyear,maxyear).count().flatMap(c->{
                        return  Mono.just(
                                new PageSupport<MovieDto>(listpaged,pagere.getPageNumber(),pagere.getPageSize(),c)
                        );
                    });
                });
    }

    @Override
    public Mono<PageSupport<MovieDto>> searchAllMovieByPage(int page, int size, String keyword) {
        PageRequest pagere = PageRequest.of(page, size);

        return movieRepository.findMovieByMovienameContains(pagere,keyword)
                .map(m->{return  Converter.MovieToDto(m);})
                .collectList()
                .<PageSupport<MovieDto>>flatMap(listpaged->{
                    return movieRepository.findMovieByMovienameContainsAndCategorieidNotNull(keyword).count().flatMap(c->{
                        return  Mono.just(
                                new PageSupport<MovieDto>(listpaged,pagere.getPageNumber(),pagere.getPageSize(),c)
                        );
                    });
                });
    }

    @Override
    public Mono<PageSupport<MovieDto>> searchAllMovieByPage(int page, int size) {
        PageRequest pagere = PageRequest.of(page, size);

        return movieRepository.findAllByIdIsNotNull(pagere)
                .map(m->{return  Converter.MovieToDto(m);})
                .collectList()
                .<PageSupport<MovieDto>>flatMap(listpaged->{
                    return movieRepository.count().flatMap(c->{
                        return  Mono.just(
                                new PageSupport<MovieDto>(listpaged,pagere.getPageNumber(),pagere.getPageSize(),c)
                        );
                    });
                });
    }
    @Override
    public Mono<PageSupport<MovieDto>> getAllMovieContainsByPage(int page, int size, String keyword) {
        PageRequest pagere =PageRequest.of(page,size);
        return movieRepository.findMovieByMovienameContainsAndIdNotNull(pagere,keyword)
                .map(m->Converter.MovieToDto(m))
                .collectList()
                .<PageSupport<MovieDto>>flatMap(listpaged->{
                    return  movieRepository.count().flatMap(c->{
                        return  Mono.just( new PageSupport<MovieDto>(listpaged,pagere.getPageNumber(),pagere.getPageSize(),c));
                    });
                });
    }

    public Mono<MovieDto>getMovieByName(String moviename)
    {
        return  movieRepository.findMovieByMovienameContains(moviename)
                .delayElement(Duration.ofMillis(5000))
                .map(Converter::MovieToDto);
    }
    @Override
    public Mono<MovieDto> saveMovie(Mono<MovieDtoRequest> movieDto, String fileName, String movieName) {

        //System.out.println("saving movie"+movieDto.block().getMoviename());

        return movieDto
                .flatMap(m -> {
                    m.setFileurl(movieName);
                    m.setImageurl(fileName);
                    return categoryService.addMovieToCategorie(
                            movieRepository.insert(
                                    Converter.DtoRequetToMovie(m)), m.getCategorieid());
                })
                .map(Converter::MovieToDto);


    }




}
