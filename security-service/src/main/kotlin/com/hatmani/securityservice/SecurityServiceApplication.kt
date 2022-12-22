package com.hatmani.securityservice

import com.hatmani.securityservice.Repository.RoleRepository
import com.hatmani.securityservice.Repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude =[SecurityAutoConfiguration::class])
class SecurityServiceApplication(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
){

 /*   @Bean
    fun init() {
        var roleA =Role(0,"ADMIN")
        var roleI =Role(0,"INVITE")
        roleRepository.save(roleA)
        roleRepository.save(roleI)
        var userA=User("karim","Karim","karim","karim@gmail.com","karim")
        var userI=User("invite","invite","invite","karim@gmail.com","invite")
        userRepository.save(userA)
        userRepository.save(userI)

    }*/
}


fun main(args: Array<String>) {
    runApplication<SecurityServiceApplication>(*args)



}