package com.hatmani.securityservice.Controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/protected")
@Validated
class EmployeeController {
    @GetMapping("/employees")
    fun  authen():String
    {
        return "your are in authentificated area"
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/manager")
    fun  manager():String
    {
        return "your are in Manager authentificated area"
    }
}