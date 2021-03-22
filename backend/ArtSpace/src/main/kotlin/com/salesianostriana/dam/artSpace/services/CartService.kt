package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.Cart
import com.salesianostriana.dam.artSpace.repositories.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService(
    private val cR : CartRepository
) {

    fun save(cart : Cart) = cR.save(cart)

}