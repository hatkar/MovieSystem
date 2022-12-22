package com.hatmani.securityservice.Controller


import com.hatmani.securityservice.Entity.Role
import com.hatmani.securityservice.Entity.User
import com.hatmani.securityservice.Repository.RoleRepository
import com.hatmani.securityservice.Repository.UserRepository
import com.hatmani.securityservice.dto.LoginRequest
import com.hatmani.securityservice.dto.SignupRequest
import com.hatmani.securityservice.dto.toUser
import com.hatmani.securityservice.Service.Jwt.JwtUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(private val authenticationManager :AuthenticationManager,
                     private val tokenProvider: JwtUtils,
                     private val userRepository:UserRepository,
                     private val roleRepository: RoleRepository,
                     private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody login: LoginRequest): ResponseEntity<String>
    {
       var authentication: Authentication =authenticationManager
           .authenticate(UsernamePasswordAuthenticationToken(login.username,login.password))
        SecurityContextHolder.getContext().authentication=authentication
        var jwt:String=tokenProvider.generateJwtToken(authentication)
        return  ResponseEntity.ok(jwt)
    }
    @PostMapping("/signupadmin")
    fun signupAdmin(@RequestBody logup: SignupRequest): ResponseEntity<Any>
    {
        println("SIGN UP ADMIN PROCESS")
     //check username exist
var user: User =logup.toUser().apply { this.password=passwordEncoder.encode(this.password) }
        var roleadmin: Role=roleRepository.findByRole("ADMIN")
            .orElse(roleRepository.save(Role("ADMIN")))
        user.role=roleadmin
        println("*****SAVING USER *****")
        println(user.toString())
        userRepository.save(user)
        return ResponseEntity.ok("Sucess Admin Resgistration")


    }
    @PostMapping("/signupinvite")
    fun signupInvite(@RequestBody logup: SignupRequest): ResponseEntity<String>
    {
        var user: User =logup.toUser().apply { this.password=passwordEncoder.encode(this.password) }
        var roleadmin: Role=roleRepository.findByRole("INVITE")
            .orElse(roleRepository.save(Role("INVITE")))
        user.role=roleadmin
        userRepository.save(user)
        return ResponseEntity.ok("Sucess Invite Resgistration")
    }
}

/*class LoginDTO(var username: String ,var password: String) {


}*/
