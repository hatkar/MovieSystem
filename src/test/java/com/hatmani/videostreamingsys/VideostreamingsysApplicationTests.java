package com.hatmani.videostreamingsys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VideostreamingsysApplicationTests {

    @Test
    void contextLoads() {
    }
//Unit Testing
    //c un pratique qui consiste a valider les plus petit morceau de code
    // pr determiner si il fournit le resultat attendu,les test unitaire doivent etre independant
    // en cas d'amilioration ou modification ils ne doivent pas etre affecte
//Integration Testing
    //ce test sont destine a verifier les combinaison de differents unite leur interactions
    //ces test son effectués selon la methode de boite noire ce qui signifie qu'il faut test
    // les interfaces desmodules sans savoir travailler sur la partie interne
//Testing Dependencies
    //1. JUnit Jupiter:platforme qui responsable de lancer le test on JVM
       //a) Mockito:c'un similateur pour les Junit Test
      //b) WebTestClient:elle  utilise pr tester les endpoints avec ou sans executer le serveur
    // 2.Reactor Test : une bibliotheque qui aide pour test les reactive composant Mono et Flux in
      // asynchronous manier elle a 2 composant principale :
        //a) StepVerifier : elle fournit un moyen de cree un script verifiable pour une sequence
        // async publisher en exprimant des attentes concernant les evenement qui se produiront lors de l'abonnement
        //b) TestPublisher: c'est un publisher qu'on peut le manipuler declancher les evenement onNext,onComplete ,onError pour le test
}
