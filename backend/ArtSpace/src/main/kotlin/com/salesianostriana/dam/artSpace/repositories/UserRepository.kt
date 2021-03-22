package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface UserRepository : JpaRepository<User,UUID> {
    fun findByUsername(username:String): Optional<User>


}