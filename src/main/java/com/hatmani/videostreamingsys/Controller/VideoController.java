package com.hatmani.videostreamingsys.Controller;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.services.CategorieService;
import com.hatmani.videostreamingsys.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
//Common Error
// Only one connection receive subscriber allowed.
//	at reactor.netty.channel.FluxReceive.startReceiver(FluxReceive.java:182) ~[reactor-netty-core-1.0.22.jar:1.0.22]
//	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
//===>ilya s subscribe sur la meme Mono
//: DBRef resolution is not supported!
//replace @DBRef avec  @DocumentReference

//@RequiredArgsConstructor
@RestController
@RequestMapping("/movies/api")
public class VideoController {
    @Autowired
    CategorieService categorieService;
    @Autowired
    MovieService movieService;
    @GetMapping(value="/categories",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<CategoryDto> categories() {


        return categorieService.getAllCategory();
    }
    @GetMapping(value="/fullcategories",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Categorie> fullcategories() {
        System.out.println("Start getting all categorie...");


        return categorieService.getAllCategory2();
    }
    @GetMapping(value="/categorie/{id}",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<CategoryDto> findcategories(@PathVariable() String id) {


        return categorieService.getCategorie(id);
    }
    @PostMapping("/addCategorie")
    //!!!!!!@RequestBody Mono<ProductDto>!!!!!! est pas @RequestBody ProductDto
    public Mono<CategoryDto> saveProduct(@RequestBody Mono<CategoryDto> categorie)
    {

        return categorieService.saveCategorie(categorie);
    }

    //Video

    @PostMapping("/addmovie")
    //!!!!!!@RequestBody Mono<ProductDto>!!!!!! est pas @RequestBody ProductDto
    public Mono<MovieDto> saveMovie(@RequestBody Mono<MovieDto> movie)
    {

        return movieService.saveMovie(movie);
    }
}
