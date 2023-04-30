package com.hatmani.securityservice.Entity

data class Person(
        //@Id
        //@GeneratedValue(strategy= GenerationType.IDENTITY) on peut l'use
        //@SequenceGenerator(name= PERSON_SEQUENCE,sequenceName = PERSON_SEQUENCE,initialValue = 1)
       // @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = PERSON_SEQUENCE )
        var id:Long=1,
        var name:String ="",
        var lastname:String?=null) {
    //in kotlin ilya pas static type alors on use
    /*companion object{
        const val PERSON_SEQUENCE:String="PERSON_SEQUENCE"
    }*/

}