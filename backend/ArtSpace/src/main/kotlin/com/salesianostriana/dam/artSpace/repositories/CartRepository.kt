package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


interface CartRepository : JpaRepository<Cart, UUID> {

}