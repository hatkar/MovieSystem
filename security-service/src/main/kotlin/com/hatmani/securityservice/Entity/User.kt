package com.hatmani.securityservice.Entity


import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


@Entity
open class User(
    var firstname: String = "",
    var lastname: String = "",
    var username: String = "",
    var email: String = "",
    var password: String = ""){


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0

    var enabled:Boolean =true
    var accountNonExpired: Boolean = true
    var accountNonLocked: Boolean = true
    var credentialsNonExpired: Boolean = true
   /* @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
        name="users_roles"
    //    joinColumns = [JoinColumn(name = "user_id")],
      //  inverseJoinColumns = [JoinColumn(name="role_id")]
    )

    lateinit var role: Role*/

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_role")
     var role: Role? = null

    constructor(user: User) : this(user.firstname, user.lastname, user.username, user.email, user.password){
        id = user.id
        firstname = user.firstname
        lastname = user.lastname
        username = user.username
        email = user.email
        password = user.password
        accountNonExpired = user.accountNonExpired
        accountNonLocked = user.accountNonLocked
        credentialsNonExpired = user.credentialsNonExpired
        enabled = user.enabled
        role = user.role
    }

    override fun toString(): String {
        println("*====>"+firstname)
        println("*====>"+lastname)
        println("*====>"+username)
        println("*====>"+email)
        println("*====>"+password)
        if (!this.username.isNullOrEmpty())
        return "User(firstname='$firstname', lastname='$lastname', username='$username', email='$email', password='$password')"
        else
            return "NULL USER"
    }

}