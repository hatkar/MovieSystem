package com.hatmani.securityservice.Config

import com.fasterxml.jackson.databind.ObjectMapper
import org.ietf.jgss.GSSException.UNAUTHORIZED
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import sun.jvm.hotspot.HelloWorld.e
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {
    //client non connecte
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
           var mapper = ObjectMapper()
                   //logger.error("Unauthorized error: {}", authException.getMessage());
                   response?.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
           if (authException != null) {
               response?.getWriter()?.write(
                   mapper.writeValueAsString(authException.message))
           }




    }
}
