package com.hatmani.videostreamingsys.Utils;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.entity.Movie;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public static CategoryDto categoryToDto(Categorie categorie) {
        System.out.println("Converting Start"+categorie);
        List<MovieDto> movies = null;

        if (categorie.getMovies() != null) {
            List<MovieDto> finalMovies = movies;
            categorie.getMovies().stream().parallel().map(m -> finalMovies.add(MovieToDto(m)));//.forEach(m->movies.add(MovieToDto(m)));//.stream(p-> movies.add(MovieToDto(p)));
            movies = finalMovies;
        }


        CategoryDto categoryDto = new CategoryDto(categorie.getId(), categorie.getName(), categorie.getDescription(),
                movies);
        System.out.println("Converting Complete");

        return categoryDto;
    }

    public static Categorie DtoToCategorie(CategoryDto dto) {
        System.out.println("COnverter :saving categorie...");
        Categorie categorie = new Categorie(dto.getId(), dto.getName(), dto.getDescription());
        return categorie;
    }

    public static MovieDto MovieToDto(Movie movie) {
        System.out.println("Converting Movie to DTO...");
        System.out.println(movie.getMoviename());
        MovieDto movieDto = new MovieDto();
        BeanUtils.copyProperties(movie, movieDto);
        return movieDto;
    }

    public static Movie DtoToMovie(MovieDto dto) {
        Movie movie = new Movie();
        BeanUtils.copyProperties(dto, movie);
        return movie;
    }
}
