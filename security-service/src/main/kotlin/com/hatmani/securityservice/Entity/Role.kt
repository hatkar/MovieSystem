package com.hatmani.securityservice.Entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long=0,

    val role: String="",


) {
    constructor(therole: String) : this(role = therole)
//constructor():this(id=1L,role="", mutableListOf())
}


