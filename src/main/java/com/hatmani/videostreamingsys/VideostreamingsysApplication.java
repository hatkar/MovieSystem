package com.hatmani.videostreamingsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


//@EnableConfigurationProperties({FileStorageProperties.class})

@SpringBootApplication
public class VideostreamingsysApplication {

  /*  @Autowired
    CategorieService categorieService;
    @Autowired
    MovieService movieService;*/

    public static void main(String[] args) {
        SpringApplication.run(VideostreamingsysApplication.class, args);
    }

   /* private void init()
    {
        System.out.println("Creation des Categories");
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Action","Last Action Movie",null)));
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Romance","Last Action Movie",null)));
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Horro","Last Action Movie",null)));
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Anime","Last Action Movie",null)));
        //Creations des movies
        //affichage des gategories
        //affichage des movies
        System.out.println("list Categories");
        categorieService.getAllCategory().toStream().forEach(categoryDto -> System.out.println(categoryDto.getName()));//.map(c-> System.out.println(c.getName()));

    }
*/
}
