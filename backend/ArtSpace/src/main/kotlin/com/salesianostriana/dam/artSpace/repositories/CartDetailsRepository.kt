package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.CartDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartDetailsRepository : JpaRepository<CartDetails, UUID> {

}