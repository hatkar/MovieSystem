package com.hatmani.videostreamingsys.services;

import com.hatmani.videostreamingsys.Exception.FileStorageException;
import com.hatmani.videostreamingsys.Exception.MovieFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorage implements IFileStorage {
            


    private final Path fileStorageLocation;
@Autowired
private ResourceLoader resourceLoader;
//private static  final String FORMAT="classpath:moviemanager/movie/%s.mp4";
    @Autowired
   // FileStorageProperties fileStorageProperties;
    public FileStorage(@Value("${file.rootDirectory}")
                            String rootDirectory) {
        System.out.println("======> root path "+rootDirectory);
        fileStorageLocation = Paths.get(rootDirectory).toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            System.out.println("ERROR throwed: Serveur n'est pas demarrer a cause de non creation de dossier racine");
            throw new FileStorageException("Serveur n'est pas demarrer a cause de non creation de dossier racine");
        }
    }

    @Override
    public String storeFile(MultipartFile file, String directory) {
        Path thefileStorageLocation;
        try {
            thefileStorageLocation = Files.createDirectories(Paths.get(fileStorageLocation.toString() + "\\" + directory));

        } catch (Exception ex) {
            throw new FileStorageException("Impossible de cree le dossier de movie");
        }
        //Normalisation de fichier
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("FileName contain Invalid Path ");
            }
            //copy file to the target location Replacing existing file with the same name

            Path targetLocation = thefileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);
            return directory + "/" + fileName;
        } catch (Exception ex) {
            throw new FileStorageException("could Not Store file " + fileName + " try again");
        }


    }
    @Override
    public Resource loadFileAsResourcefromdirectory(String directory, String fileName){
        Path thefileStorageLocation;
        thefileStorageLocation =Paths.get(this.fileStorageLocation.toString()+"/"+directory);

        try{
            Path filePath =thefileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else
            {
                throw new MovieFileNotFoundException("File not Found"+fileName);
            }
        }catch(MalformedURLException ex)
        {
            throw new MovieFileNotFoundException("File Not Found");
        }
    }
    public Mono<Resource> getVideofrom(String directory, String fileName) {
       return Mono.fromSupplier(()->{
           System.out.println("******* getting movie dir :"+directory+" file : "+fileName);
            Path thefileStorageLocation;
            thefileStorageLocation =Paths.get(this.fileStorageLocation.toString()+"/"+directory);
           System.out.println("2******* getting movie");
            try{
                Path filePath =thefileStorageLocation.resolve(fileName).normalize();
                Resource resource = new UrlResource(filePath.toUri());
                System.out.println("3******* getting movie"+filePath.toUri());
                if(resource.exists()){
                    System.out.println("4******* getting movie");
                    return resource;
                }else
                {
                    throw new MovieFileNotFoundException("File not Found"+fileName);
                }
            }catch(MalformedURLException ex)
            {
                throw new MovieFileNotFoundException("File Not Found");
            }
        });
        //return Mono.fromSupplier(()->resourceLoader.getResource(String.format(FORMAT,title)) );
    }

   /* @Override
    public Mono<Resource> getVideo(String title) {
        return Mono.fromSupplier(()->resourceLoader.getResource(String.format(FORMAT,title)) );
    }*/

}
