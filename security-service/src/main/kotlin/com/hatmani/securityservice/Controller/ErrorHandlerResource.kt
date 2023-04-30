package com.hatmani.securityservice.Controller

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
//@ControllerAdvice
class ErrorHandlerResource {
    @ExceptionHandler(RuntimeException::class)
    fun handleIllegalState(ex:RuntimeException): ResponseEntity<ErrorResponse> {
        return   ResponseEntity.badRequest()
            .body(ErrorResponse(message=""+ex.message ))
    }
    @ExceptionHandler(ExpiredJwtException::class)

    fun handleExpiredJwtException(ex: ExpiredJwtException): ResponseEntity<ErrorResponse> {
        return   ResponseEntity.badRequest()
            .body(ErrorResponse(message=""+ex.message ))
    }

    class ErrorResponse(var title:String="Bad Request",var message:String="",val dateTime: LocalDateTime = LocalDateTime.now()) {

    }
}