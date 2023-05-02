package com.hatmani.videostreamingsys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


//@EnableConfigurationProperties({FileStorageProperties.class})

@SpringBootApplication
@EnableEurekaClient
public class VideostreamingsysApplication {
    @Value("${spring.data.mongodb.database}")
    String profile;
  /*  @Autowired
    CategorieService categorieService;
    @Autowired
    MovieService movieService;*/


    public static void main(String[] args) {
        SpringApplication.run(VideostreamingsysApplication.class, args);

    }
@Bean
     void init()
    {
        System.out.println("profile mongodb :"+profile);


   /*     System.out.println("Creation des Categories");
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Action","Last Action Movie",null)));
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Romance","Last Action Movie",null)));
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Horro","Last Action Movie",null)));
        categorieService.saveCategorie(Mono.just(new CategoryDto(null,"Anime","Last Action Movie",null)));
        //Creations des movies
        //affichage des gategories
        //affichage des movies
        System.out.println("list Categories");
        categorieService.getAllCategory().toStream().forEach(categoryDto -> System.out.println(categoryDto.getName()));//.map(c-> System.out.println(c.getName()));
*/
    }

}
