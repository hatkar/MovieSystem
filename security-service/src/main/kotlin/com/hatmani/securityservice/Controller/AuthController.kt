package com.hatmani.securityservice.Controller


import com.hatmani.securityservice.Entity.Role
import com.hatmani.securityservice.Entity.User
import com.hatmani.securityservice.Repository.RoleRepository
import com.hatmani.securityservice.Repository.UserRepository
import com.hatmani.securityservice.Service.Jwt.JwtUtils
import com.hatmani.securityservice.Service.Jwt.UserDetailsImpl
import com.hatmani.securityservice.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["http://localhost:4200"], maxAge = 3600) //@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers"}, exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"})

class AuthController(private val authenticationManager: AuthenticationManager,
                     private val tokenProvider: JwtUtils,
                     private val userRepository: UserRepository,
                     private val roleRepository: RoleRepository,
                     private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/signin")
   fun authenticateUser(@RequestBody login: LoginRequest): ResponseEntity<Any>
    //fun authenticateUser(): Any
    {
        println("SIGN IN REQUEST ...")
        val Logeduser: LoginResponse = loginProcess(login.username,login.password)

        return ResponseEntity.ok(Logeduser)



    }

    private fun loginProcess(username: String, password: String): LoginResponse {
        var authentication: Authentication = authenticationManager
                .authenticate(UsernamePasswordAuthenticationToken(username, password))
        SecurityContextHolder.getContext().authentication = authentication
        var jwt: String = tokenProvider.generateJwtToken(authentication)
        var userPrincipal: UserDetailsImpl = authentication.principal as UserDetailsImpl
println("********"+userPrincipal.getRole())
        println("********")
        userPrincipal.authorities.forEach { ga->println("+++++"+ga.authority.toString()); }
        val Logeduser: LoginResponse = userPrincipal.user.toLoginResponse().apply { this.token = jwt }
        return Logeduser
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
        println("SIGN IN PROCESS ...")
        val Logeduser: LoginResponse = loginProcess(logup.username,logup.password)

        return ResponseEntity.ok(Logeduser)


    }
    @PostMapping("/signupinvite")
    fun signupInvite(@RequestBody logup: SignupRequest): ResponseEntity<Any>
    {
        println("SIGN UP INVITE PROCESS")

        var user: User =logup.toUser().apply { this.password=passwordEncoder.encode(this.password) }
        var roleadmin: Role=roleRepository.findByRole("INVITE")
            .orElse(roleRepository.save(Role("INVITE")))
        user.role=roleadmin
        userRepository.save(user)

        println("SIGN IN PROCESS ...")
        val Logeduser: LoginResponse = loginProcess(logup.username,logup.password)

        return ResponseEntity.ok(Logeduser)
    }
    @GetMapping("/ping")
    fun ping(): ResponseEntity<String>
    {

        return ResponseEntity.ok("Sucess Ping ...")
    }
}


/*class LoginDTO(var username: String ,var password: String) {


}*/
