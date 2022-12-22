package com.hatmani.securityservice.Service.Jwt

import com.hatmani.securityservice.Entity.User
import com.hatmani.securityservice.Repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) :  org.springframework.security.core.userdetails.UserDetailsService {




    override fun loadUserByUsername(username: String?): UserDetails {
        try {
            val user: User? = userRepository.findByUsername(username!!)
            println("loadUserByUsername*********")
            println(user.toString())
            return UserDetailsImpl(user!!)
        } catch (ex: Exception) {
            throw Exception("User Not Found with username: " + username)
        }
    }


}