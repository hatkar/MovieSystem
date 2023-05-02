package com.hatmani.videostreamingsys.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hatmani.videostreamingsys.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {

    private String id;
    private String name;
    private String description;
    private List<MovieDto> movies;//=new ArrayList<MovieDto>();


}
