package com.hatmani.securityservice.dto

import com.hatmani.securityservice.Entity.User
import com.hatmani.securityservice.Service.Jwt.UserDetailsImpl

fun SignupRequest?.toUser():User{
    return User(firstname= this?.firstname ?: "",
        lastname=this?.lastname?:"",
        username=this?.username?:"",
        email=this?.email?:"",
        password=this?.password?:"" )
}
//extension de class UserDetailsImpl avec cette methode de conversion
fun UserDetailsImpl.toLoginResponse(): LoginResponse {
    return LoginResponse(firstname= this?.user.firstname ?: "",
            lastname=this?.user.lastname?:"",
            username=this?.user.username?:"",
            email=this?.user.email?:"" ,
            token="")
}
//extension de class UserDetailsImpl avec cette methode de conversion
/*
fun Person?.toPersonResponse(): PersonResponse {
    return PersonResponse(
        id = this?.id ?: 1L,fullname = "${this?.name } ${this?.lastname}")
}
 */
fun User?.toLoginResponse(): LoginResponse {
    println("detected role:"+this?.role)
    return LoginResponse(firstname= this?.firstname ?: "",
            lastname=this?.lastname?:"",
            username=this?.username?:"",
            email=this?.email?:"" ,
            token="",
    role= this?.role!!.role)
}
