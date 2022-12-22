package com.hatmani.videostreamingsys.services;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.io.IOException;
import java.net.URI;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReactiveFileStorage {
    //@return the URL format example: http://localhost:8080
    public static  String baseUrl(ServerHttpRequest request)
    {
        URI uri = request.getURI();
        String port="";
        if(uri.getPort()!=80){
            port=String.format("%s",uri.getPort());
        }
        return  String.format("%s://%s:%s",uri.getScheme(),uri.getHost(),port);
    }
    public static Boolean createFile(FilePart filePart,String folder,String fileName)  {
       try {
           String fullPath = folder + "/" + fileName;
           Path path = Files.createFile(Paths.get(fullPath).toAbsolutePath().normalize());
           AsynchronousFileChannel chanel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
           DataBufferUtils.write(filePart.content(), chanel, 0)
                   .doOnComplete(() -> {
                       try {
                           chanel.close();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }).subscribe();
           return true;
       }catch (IOException e)
       {
           System.out.println("Error Create File"+e.getMessage());
       }
       return false;
    }
}
