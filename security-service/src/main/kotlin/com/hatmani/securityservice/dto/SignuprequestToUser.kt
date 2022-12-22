package com.hatmani.securityservice.dto

import com.hatmani.securityservice.Entity.User

fun SignupRequest?.toUser():User{
    return User(firstname= this?.firstname ?: "",
        lastname=this?.lastname?:"",
        username=this?.username?:"",
        email=this?.email?:"",
        password=this?.password?:"" )
}