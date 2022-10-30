package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Utils.Converter;
import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import com.hatmani.videostreamingsys.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

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
    public Mono<MovieDto> saveMovie(Mono<MovieDto> movieDto) {



        return movieDto
                .flatMap(m->categoryService.addMovieToCategorie(movieRepository.insert(Converter.DtoToMovie(m)),m.getCategorieid()))
                .map(Converter::MovieToDto);





    }


}
