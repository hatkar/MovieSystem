package com.hatmani.securityservice.Service.Jwt

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
 class CustomAccessDeniedHandler : AccessDeniedHandler {
 //si le client connecte et n'est pas autorise



    override fun handle(
        p0: HttpServletRequest?,
        p1: HttpServletResponse?,
        p2: org.springframework.security.access.AccessDeniedException?
    ) {
        p1?.addHeader("access_denied_reason", "not_authorized")
      // p1?.sendError(403, "Access Denied")
        var mapper = ObjectMapper()

        p1?.setStatus(HttpServletResponse.SC_FORBIDDEN);
        if (p2 != null) {
            p1?.getWriter()?.write(
                mapper.writeValueAsString(p2.message))
        };
    }
}