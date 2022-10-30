package com.hatmani.videostreamingsys.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "movies")

public class Movie {
    @Id
    private String id;
    private String moviename;
    private String description;
    private String fileurl;
    private String imageurl;


}
