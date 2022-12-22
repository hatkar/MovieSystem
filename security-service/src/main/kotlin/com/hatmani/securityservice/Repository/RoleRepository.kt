package com.hatmani.securityservice.Repository

import com.hatmani.securityservice.Entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository:JpaRepository<Role,Long> {
    fun findByRole(rolename: String): Optional<Role>
}