package com.hatmani.videostreamingsys.Controller;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Dto.MovieDtoRequest;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import com.hatmani.videostreamingsys.services.CategorieService;
import com.hatmani.videostreamingsys.services.FileStorage;
import com.hatmani.videostreamingsys.services.MovieService;
import com.hatmani.videostreamingsys.services.ReactiveFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
@RestController

@RequestMapping("/admin/api")
public class AdminController {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    CategorieService categorieService;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    MovieService movieService;
    private final Path basePath = Paths.get("./src/main/resources/upload/");
    @Autowired
    private FileStorage fileStorageservice;

    @PostMapping("/addCategorie")
    //!!!!!!@RequestBody Mono<ProductDto>!!!!!! est pas @RequestBody ProductDto
    public Mono<CategoryDto> saveProduct(@RequestBody Mono<CategoryDto> categorie) {

        return categorieService.saveCategorie(categorie);
    }

    //Video
    @PostMapping("/addfile")
    public Mono<MovieDto> savefile(@RequestPart("movie") Mono<FilePart> movie) {
        System.out.println("add file");

        //  fileStorageservice.storeFile(movie.block(),"picture1");
        //fileStorageservice.storeFile(movie.block().getFilemovie(),"movie1");
        return Mono.empty();
        //return movieService.saveMovie(Mono.just(movie.block().getMovie()));
    }
    @PostMapping("/addmovie")
    //!!!!!!@RequestBody Mono<ProductDto>!!!!!! est pas @RequestBody ProductDto
    public Mono<MovieDto> saveMovie(@RequestBody Mono<MultipartFile> movie) {
        System.out.println("===>add Movie");
        System.out.println(movie);

        fileStorageservice.storeFile(movie.block(), "picture1");
        //fileStorageservice.storeFile(movie.block().getFilemovie(),"movie1");
        return Mono.empty();
        //return movieService.saveMovie(Mono.just(movie.block().getMovie()));
    }


    @PostMapping(value = "uploadmovie", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<MovieDto>> uploadmovie(@RequestPart("file") FilePart filePart,
                                                      @RequestPart("moviefile") FilePart moviePart,
                                                      @RequestPart("movie") Mono<MovieDtoRequest> movie,
                                                      ServerHttpRequest httpRequest) {
        System.out.println("calling save all");
        //savemoviewithfile(movie,filePart,moviePart,httpRequest);
        //photoadress.doOnNext()
        //System.out.println(movie.subscribe());
        return savemoviewithfile(movie, filePart, moviePart, httpRequest);

        // return Mono.just(movie);
    }


    Mono<ResponseEntity<MovieDto>> savemoviewithfile(Mono<MovieDtoRequest> movie, FilePart filePart, FilePart moviePart, ServerHttpRequest httpRequest) {
        System.out.println("satrt savin process");
        String fileName = System.nanoTime() + "_" + filePart.filename();
        String movieName = System.nanoTime() + "_" + moviePart.filename();
        return movieService.saveMovie(movie, fileName, movieName)
                .subscribeOn(Schedulers.boundedElastic())
                .flatMap(createdentity -> {
                    Object resp = false;
                    String baseUrl = ReactiveFileStorage.baseUrl(httpRequest);
                    resp = baseUrl + "/moviedetails/" + "llkljlsdhhsdhkhs";
                    return Mono.just(new ResponseEntity<>(createdentity, HttpStatus.CREATED));
                    // return Mono.just(resp.toString());
                })
                .doOnNext(fp -> savephoto(filePart, fileName).subscribe())
                .doOnNext(fm -> savemovie(moviePart, movieName).subscribe());






    }
    Mono<Boolean> savephoto(FilePart filePart, String fileName) {
        //  String fileName =System.nanoTime()+"_"+filePart.filename();
        return Mono.<Boolean>fromCallable(() -> {
            return ReactiveFileStorage.createFile(filePart, uploadDir + "/photo", fileName);
        });
    }

    Mono<Boolean> savemovie(FilePart filePart, String fileName) {
        //  String fileName =System.nanoTime()+"_"+filePart.filename();
        return Mono.<Boolean>fromCallable(() -> {
            return ReactiveFileStorage.createFile(filePart, uploadDir + "/movie", fileName);
        });
    }

}
