package com.hatmani.videostreamingsys.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDto {
    private String id;
    private String moviename;
    private String description;
    private String fileurl;
    private String imageurl;
   // @JsonIgnore()
    private String categorieid;
    private String duration;
    private String releaseyear;


}
