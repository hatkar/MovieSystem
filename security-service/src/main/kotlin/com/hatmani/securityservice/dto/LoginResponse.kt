package com.hatmani.securityservice.dto

data class LoginResponse(var firstname: String = "",
                         var lastname: String = "",
                         var username: String = "",
                         var email: String = "",
                         var token: String = "",

                         var role: String = "") {


    override fun toString(): String {
        println("*====>" + firstname)
        println("*====>" + lastname)
        println("*====>" + username)
        println("*====>" + email)
        //  println("*====>"+password)
        if (!this.username.isNullOrEmpty())
            return "User(firstname='$firstname', lastname='$lastname', username='$username', email='$email')"
        else
            return "NULL USER"
    }
}