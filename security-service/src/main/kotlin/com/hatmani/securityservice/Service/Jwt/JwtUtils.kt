package com.hatmani.securityservice.Service.Jwt

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class JwtUtils {
    //il faut ajouter \ et lateinit
    @Value("\${app.jwtSecret}")
    private lateinit var jwtSecret:String
    @Value("\${app.jwtExpirationInMs}")
    private var jwtExpirationInMs:Int = 1000000
    public fun generateJwtToken(authentication:Authentication):String
    {
        var userPrincipal: UserDetailsImpl = authentication.principal as UserDetailsImpl
        var now :Date= Date()
        var expir:Date = Date(now.time+jwtExpirationInMs)
        return  Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(expir)
            .signWith(SignatureAlgorithm.HS384,jwtSecret)
            .compact()

    }
    public fun getUserNameFromJwtToken(token:String?):String
    {
        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
            .subject
    }

    public fun validateJwtToken(authToken: String?, request: HttpServletRequest, response: HttpServletResponse):Boolean
    {
        try {
            Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(authToken)
            return true
        }catch (ex: SignatureException){
            println("SIGNATURE EXCEPTION")
            senderror(response, ex)
            //SignatureException
        }catch (ex: MalformedJwtException){
            println("MalformedJwtException EXCEPTION")
            senderror(response, ex)
        }catch (ex: ExpiredJwtException){
            println("ExpiredJwtException EXCEPTION ")
            senderror(response, ex)


        }catch (ex: UnsupportedJwtException){
            println("UnsupportedJwtException EXCEPTION")
            senderror(response, ex)
        }catch (ex:IllegalArgumentException){
            println("IllegalArgumentException EXCEPTION")
            senderror(response, ex)
        }

        return false
    }

    private fun senderror(response: HttpServletResponse, ex: Exception) {
        var mapper = ObjectMapper()
        //logger.error("Unauthorized error: {}", authException.getMessage());
        response?.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response?.getWriter()?.write(
            mapper.writeValueAsString(ex.localizedMessage)
        )
    }


}