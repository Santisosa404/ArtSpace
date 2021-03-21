package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.CartDetails
import com.salesianostriana.dam.artSpace.repositories.CartDetailsRepository
import org.springframework.stereotype.Service

@Service
class CartDetailsService(
    private val cartDR : CartDetailsRepository
) {
    fun save(cartDetails: CartDetails) = cartDR.save(cartDetails)
}