package com.hatmani.securityservice.Repository

import com.hatmani.securityservice.Entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:JpaRepository<User,Long> {
    fun findByUsername(username: String): User
}