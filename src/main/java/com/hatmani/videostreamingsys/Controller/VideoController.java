package com.hatmani.videostreamingsys.Controller;

import com.hatmani.videostreamingsys.Dto.CategoryDto;
import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Dto.PageSupport;
import com.hatmani.videostreamingsys.entity.Categorie;
import com.hatmani.videostreamingsys.repository.CategorieRepository;
import com.hatmani.videostreamingsys.services.CategorieService;
import com.hatmani.videostreamingsys.services.FileStorage;
import com.hatmani.videostreamingsys.services.MovieService;
import com.sun.tools.javac.comp.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.stream.Collectors;
//Common Error
// Only one connection receive subscriber allowed.
//	at reactor.netty.channel.FluxReceive.startReceiver(FluxReceive.java:182) ~[reactor-netty-core-1.0.22.jar:1.0.22]
//	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException:
//===>ilya s subscribe sur la meme Mono
//: DBRef resolution is not supported!
//replace @DBRef avec  @DocumentReference

//@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
//@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})
@RestController

@RequestMapping("/movies/api")
public class VideoController {
    private static final int DELAY_PER_ITEM_MS = 4000;
    @Autowired
    CategorieService categorieService;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    MovieService movieService;
    private final Path basePath = Paths.get("./src/main/resources/upload/");
    @Autowired
    private FileStorage fileStorageservice;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CategoryDto> categories() {
        return categorieService.getAllCategory();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/movies")
    public Flux<MovieDto> GetAllMovies() {
        System.out.println("Calling All Movies");
        return movieService.GetAllMovie();
    }

    @GetMapping(value = "/categoriesbypage")
    public Flux<Categorie> getcategorieFlux(final @RequestParam(name = "page") int page,
                                            final @RequestParam(name = "size") int size) {
        System.out.println("page :" + page + " Size :" + size);
        //  return quoteMongoReactiveRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, size))
        //        .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
        PageRequest pagere = PageRequest.of(page, size);
        System.out.println(pagere);
        return categorieRepository.findAllByIdNotNullOrderByIdAsc(pagere);
        //.delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    @GetMapping(value = "/pageablecategories")
    public Mono<PageSupport<CategoryDto>> getcategoriepage(final @RequestParam(name = "page") int page,
                                                           final @RequestParam(name = "size") int size) {
        System.out.println("page :" + page + " Size :" + size);

        return categorieService.getAllCategoriesByPage(page, size);

    }

    @GetMapping(value = "/pageablecategoriescontain")
    public Mono<PageSupport<CategoryDto>> getcategoriepagecontain(final @RequestParam(name = "page") int page,
                                                                  final @RequestParam(name = "size") int size,
                                                                  final @RequestParam(name = "keyword") String keyword) {
        System.out.println("page :" + page + " Size :" + size);
        if (keyword == "") return categorieService.getAllCategoriesByPage(page, size);
        return categorieService.getAllCategoriescontainByPage(page, size, keyword);

    }

    @GetMapping(value = "/fullcategories", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Categorie> fullcategories() {
        System.out.println("Start getting all categorie...");


        return categorieService.getAllCategory2();
    }

    @GetMapping(value = "/categorie/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<CategoryDto> findcategories(@PathVariable() String id) {


        return categorieService.getCategorie(id);
    }

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

 /*   @PostMapping("file/single")
    public Mono<Void> upload(@RequestPart("user-name") String name,
                             @RequestPart("fileToUpload") Mono<FilePart> filePartMono){
        System.out.println("user : " + name);
        return  filePartMono
                .doOnNext(fp -> System.out.println("Received File : " + basePath.resolve(fp.filename())))
                .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
                .then();
    }
    @PostMapping("file/multi")
    public Mono<Void> upload(@RequestPart("files") Flux<FilePart> partFlux){
        return  partFlux
                .doOnNext(fp -> System.out.println(fp.filename()))
                .flatMap(fp -> fp.transferTo(basePath.resolve(fp.filename())))
                .then();
    }*/


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

    @GetMapping("/movie/{name}")
    public Mono<ResponseEntity<MovieDto>> getMovie(@PathVariable String name) {
        return movieService.getMovieByName(name)
                .map(mov -> new ResponseEntity<>(mov, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        //.delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    @GetMapping("/bycategorie/{idcategory}")
    public Flux<MovieDto> getMovieByCategorie(@PathVariable String idcategory) {
        System.out.println("CALLING MOVIE BY CATEGORIE");
        return movieService.GetAllMovieOfCatgory(idcategory);
    }

    @GetMapping(value = "/pageablemovieofcategorie")
    public Mono<PageSupport<MovieDto>> pageablemovieofcategorie(final @RequestParam(name = "page") int page,
                                                                final @RequestParam(name = "size") int size,
                                                                final @RequestParam(name = "idcategory") String idcategory) {
        return movieService.getAllMovieOfCategoryByPage(page, size, idcategory);
    }

    @GetMapping(value = "/searchmovieofcategorie")
    public Mono<PageSupport<MovieDto>> searchpageablemovieofcategorie(final @RequestParam(name = "page") int page,
                                                                      final @RequestParam(name = "size") int size,
                                                                      final @RequestParam(name = "idcategory") String idcategory,
                                                                      final @RequestParam(name = "keyword") String keyword) {
        //PB Majiscule Miniscule
        //cas avec categorie


        if (idcategory != "") {
            //avec keyword
            if (keyword != "") {
                System.out.println("AVEC CATEGORIE ET AVEC KEYWORD" + idcategory);
                return movieService.getAllMovieOfCategoryByPage(page, size, idcategory, keyword);
            }
            //sans keyword
            else {
                System.out.println("AVEC CATEGORIE ET SANS KEYWORD" + idcategory);
                return movieService.getAllMovieOfCategoryByPage(page, size, idcategory);
            }
        } else
        // without categorie//OK
        {

            //avec keyword
            if (keyword != "") {
                System.out.println("SANS CATEGORIE ET AVEC KEYWORD");
                return movieService.searchAllMovieByPage(page, size, keyword);
            } else {
                System.out.println("SANS CATEGORIE ET SANS KEYWORD");
                //sans keyword
                return movieService.searchAllMovieByPage(page, size);
            }
        }


        //return movieService.getAllMovieOfCategoryByPage(page, size, idcategory);

    }

    @GetMapping(value = "/searchmovieofcategorieandyear")
    public Mono<PageSupport<MovieDto>> searchpageablemovieofcategorieandyear(final @RequestParam(name = "page") int page,
                                                                             final @RequestParam(name = "size") int size,
                                                                             final @RequestParam(name = "idcategory",defaultValue = "") String idcategory,
                                                                             final @RequestParam(name = "minyear",defaultValue = "1940") String minyear,
                                                                             final @RequestParam(name = "maxyear",defaultValue = "2023") String maxyear) {
Integer mindate=Integer.parseInt(minyear)-1;
Integer maxdate=Integer.parseInt(maxyear)+1;
             //   System.out.println("AVEC CATEGORIE ET AVEC KEYWORD" + idcategory);
               return movieService.getAllMovieOfCategoryAndDateByPage(page, size, idcategory, mindate.toString(),maxdate.toString());


//return  null;

    }
    @GetMapping(value = "/searchmoviecontainbypage")
    public Mono<PageSupport<MovieDto>> searchpageablemovieofcategorieandyear(final @RequestParam(name = "page") int page,
                                                                             final @RequestParam(name = "size") int size,
                                                                             final @RequestParam(name = "keyword",defaultValue = "") String keyword) {

        return movieService.getAllMovieContainsByPage(page,size,keyword);


//return  null;

    }
}
