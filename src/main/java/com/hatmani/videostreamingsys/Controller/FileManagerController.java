package com.hatmani.videostreamingsys.Controller;


import com.hatmani.videostreamingsys.services.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

//import javax..servlet.http.HttpServletRequest;
@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
//@RequestMapping("/api")
@RequestMapping("/api/ressources")
public class FileManagerController {
    @Autowired
    private FileStorage fileStorageservice;

    @GetMapping("/ping")
    public Mono<String> ping() {
        return Mono.just("Server Up");
    }

    /***** PARTIE GETTING MOVIE *****/
    @GetMapping(value = "/video/{dir}/{fileName:.+}", produces = "application/octet-stream")
    public Mono<Resource> downloadvideo(@PathVariable String dir, @PathVariable String fileName) {//download video
        System.out.println("/video/" + fileName);
        return fileStorageservice.getVideofrom(dir, fileName);
    }

    @GetMapping(value = "/streamvideo/{dir}/{fileName:.+}", produces = "application/octet-stream")
    public Mono<Resource> streamvideo(@PathVariable String dir, @PathVariable String fileName, @RequestHeader("Range") String range) {//Streamin de video
        System.out.println("/stream/" + range);
        return fileStorageservice.getVideofrom(dir, fileName);
    }


    /***** PARTIE GETTING PHOTO *****/


    @GetMapping("/{dir}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String dir, @PathVariable String fileName) {
        Resource resource = fileStorageservice.loadFileAsResourcefromdirectory(dir, fileName);
        //Try to determine file's content type
        String contentType = null;
        try {
            //  contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (Exception ex) {
            //logger.info("Could not determine file type.");
            //return ResponseEntity.ok().body("");//.contentType("application/octet-stream").bo;
        }
        //Fallback th the default content type if could not found
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
