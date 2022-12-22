package com.hatmani.videostreamingsys.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDtowithfile {
    private String id;
    private String name;
    private String description;
    private List<MovieDto> movies;
    MultipartFile pictureFile;
    MultipartFile movieFile;
}
