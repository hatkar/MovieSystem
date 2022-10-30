package com.hatmani.videostreamingsys.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface IFileStorage {
    String storeFile(MultipartFile file, String directory);

    Resource loadFileAsResourcefromdirectory(String directory, String fileName);
//public Mono<Resource> getVideo(String title);

}
