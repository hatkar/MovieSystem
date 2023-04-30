package com.hatmani.securityservice

import com.hatmani.securityservice.Entity.Role
import com.hatmani.securityservice.Entity.User
import com.hatmani.securityservice.Repository.RoleRepository
import com.hatmani.securityservice.Repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

import org.springframework.cloud.netflix.eureka.EnableEurekaClient


import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication(exclude =[SecurityAutoConfiguration::class])
@EnableEurekaClient
class SecurityServiceApplication(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
){

   @Bean
    fun init() {
        var roleA = Role(0,"ADMIN")
        var roleI =Role(0,"INVITE")
        roleRepository.save(roleA)
        roleRepository.save(roleI)
        var userA= User("karim","Karim","hatkar","karim@gmail.com",this.passwordEncoder.encode("hatkar"))
       userA.role=roleA
        var userI=User("invite","invite","invite","karim@gmail.com",this.passwordEncoder.encode("invite"))
       userI.role=roleI
        userRepository.save(userA)
        userRepository.save(userI)

    }
}


fun main(args: Array<String>) {
    runApplication<SecurityServiceApplication>(*args)



}