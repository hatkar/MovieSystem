package com.hatmani.securityservice.Config

import com.hatmani.securityservice.Service.Jwt.JwtUtils
import com.hatmani.securityservice.Service.Jwt.UserDetailsServiceImpl
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(private val jwtUtils: JwtUtils,
                      private val userDetailsService: UserDetailsServiceImpl
):OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("DOFILTERINTERNAL LAUNCHED")
        try {
            val jwt= parseJwt(request)
            if (StringUtils.hasText(jwt) && jwtUtils.validateJwtToken(jwt,request,response) )
            {

                val username:String=jwtUtils.getUserNameFromJwtToken(jwt)
                var userDetails:UserDetails?=userDetailsService.loadUserByUsername(username)
                var authentication: UsernamePasswordAuthenticationToken
                        =UsernamePasswordAuthenticationToken(userDetails,null,userDetails?.authorities)
                authentication.details=WebAuthenticationDetailsSource().buildDetails(request)
                println("*************************************")
                println(authentication.principal.toString())
                SecurityContextHolder.getContext().authentication=authentication
            }

        }catch (ex:Exception)
        {
            logger.error("Error geting User Authentification"+ex.message+" "+ex.javaClass)
        }
        filterChain.doFilter(request,response)
    }
}
private fun parseJwt(request: HttpServletRequest): String? {
    var bearerToken: String = request.getHeader("Authorization")
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {

        return bearerToken.substring(7, bearerToken.length)
    }
    return null
}