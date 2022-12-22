package com.hatmani.securityservice.Entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var role: String,
    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("roles")
    var users:MutableList<User> =mutableListOf()

) {
    constructor(role: String) : this(id=1L,role=role, mutableListOf())
}


