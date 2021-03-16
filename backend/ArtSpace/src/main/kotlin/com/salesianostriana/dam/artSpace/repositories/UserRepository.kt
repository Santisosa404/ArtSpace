package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User,UUID> {
}