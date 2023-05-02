package com.hatmani.videostreamingsys.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "categories")

public class Categorie {
    @Id
    private String id;
    private String name;
    private String description;
   @DocumentReference
    //@DocumentReference(lazy = true)
    List<Movie> movies;

    public Categorie(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
