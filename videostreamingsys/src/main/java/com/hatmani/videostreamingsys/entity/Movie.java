package com.hatmani.videostreamingsys.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "movies")

public class Movie {
    @Id
    private String id;
    @Indexed(unique = true)
    private String moviename;
    private String description;
    private String fileurl;
    private String imageurl;
    private String duration;
    private String releaseyear;
    private String categorieid;


}
