package com.hatmani.videostreamingsys.Controller;

import com.hatmani.videostreamingsys.Dto.CategoryDtowithfile;
import com.hatmani.videostreamingsys.Dto.MovieDto;
import com.hatmani.videostreamingsys.Dto.MovieDtoRequest;
import com.hatmani.videostreamingsys.services.MovieService;
import com.hatmani.videostreamingsys.services.ReactiveFileStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Paths;

import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 36000)
@Slf4j
@RestController
//@RequestMapping("file")
@RequestMapping("/movies/api/file")
public class ReactiveFileController {
    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    MovieService movieService;
   /* @PostMapping("upload")
    public Mono<String> upload (@RequestPart("file")FilePart filePart, ServerHttpRequest httpRequest)
    {
        String fileName =System.nanoTime()+"_"+filePart.filename();
        return Mono.<Boolean>fromCallable(()->{
            return ReactiveFileStorage.createFile(filePart,uploadDir,fileName);
        })
                .subscribeOn(Schedulers.boundedElastic())
                .<String>flatMap(createFileState->{
                    Object resp =false;
                    if(createFileState){
                        String baseUrl = ReactiveFileStorage.baseUrl(httpRequest);
                        resp=baseUrl+"/file/download/"+fileName;
                    }
                    return  Mono.just(resp.toString());
                });

    }*/

/*Suppressed Methode and Moved to AdminController
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
    }*/


    // fonctunion save and return http://localhost:9292/file/download/107454078117944_cover5.jpg
  /*  Mono<String> save(FilePart filePart, ServerHttpRequest httpRequest)
    {
        String fileName =System.nanoTime()+"_"+filePart.filename();
        return Mono.<Boolean>fromCallable(()->{
            return ReactiveFileStorage.createFile(filePart,uploadDir,fileName);
        })
                .subscribeOn(Schedulers.boundedElastic())
                .<String>flatMap(createFileState->{
                    Object resp =false;
                    if(createFileState){
                        String baseUrl = ReactiveFileStorage.baseUrl(httpRequest);
                        resp=baseUrl+"/file/download/"+fileName;
                    }
                    return  Mono.just(resp.toString());
                });


    }*/
    /*Suppressed Methode and Moved to AdminController
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
*/

    //*****
    Mono<Boolean> saveEntity() {
        System.out.println("saving entity");
        return Mono.just(true);
    }

    @GetMapping(value = "/download/{fileName}", produces = APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<Resource>> download(@PathVariable("fileName") String fileName) throws Exception {
        return Mono.<Resource>fromCallable(() -> {
            String fileLocation = uploadDir + "/" + fileName;
            String path = Paths.get(fileLocation).toAbsolutePath().normalize().toString();
            return new FileSystemResource(path);
        })
                .subscribeOn(Schedulers.boundedElastic())
                .<ResponseEntity<Resource>>flatMap(resource -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentDispositionFormData(fileName, fileName);
                    return Mono.just(ResponseEntity.ok().cacheControl(CacheControl.noCache())
                            .headers(headers)
                            .body(resource));
                });
    }

}
