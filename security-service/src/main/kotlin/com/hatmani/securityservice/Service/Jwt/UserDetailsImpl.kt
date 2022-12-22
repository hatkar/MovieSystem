package com.hatmani.securityservice.Service.Jwt

import com.hatmani.securityservice.Entity.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl() : UserDetails {
    var user=User("","","","","")

    constructor(userdb:User):this()
    {user=userdb


    }



    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

       return mutableListOf(SimpleGrantedAuthority(user.role?.role ?: "NONE"))
    }

    override fun getPassword(): String {
        println("!!!!!*-*-*-*-*-*-*-*-*--*-*-*-*-!!!!")
       println(user.username)
        println(user.password)
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }


    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}