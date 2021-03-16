package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val uR : UserRepository
) {
    fun findByUsername(username : String) = uR.findByUsername(username)

    fun findById(id : UUID) = uR.findById(id)
}