package com.hatmani.videostreamingsys.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MovieDtoRequest {

    private String moviename;
    private String description;

    private String categorieid;
    private String fileurl;
    private String imageurl;
    private String duration;
    private String releaseyear;
  //  private String  categoryid;

}
