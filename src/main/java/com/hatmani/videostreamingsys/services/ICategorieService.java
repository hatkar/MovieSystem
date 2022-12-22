package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Dto.PageSupport;
import com.hatmani.videostreamingsys.entity.Categorie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICategorieService {
    Flux<CategoryDto> getAllCategory();

    Flux<Categorie> getAllCategory2();

    Mono<CategoryDto> getCategorie(String id);

    Mono<CategoryDto> saveCategorie(Mono<CategoryDto> dtoMono);

    Mono<CategoryDto> updateCategorie(Mono<CategoryDto> dtoMono, String id);

    Mono<PageSupport<CategoryDto>> getAllCategoriesByPage(int page, int size);

    Mono<PageSupport<CategoryDto>> getAllCategoriescontainByPage(int page, int size, String keyword);
}
