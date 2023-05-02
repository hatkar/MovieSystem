package com.hatmani.videostreamingsys.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileStorageException extends RuntimeException{
    private String message;
}
