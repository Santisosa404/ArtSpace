package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.Buy
import com.salesianostriana.dam.artSpace.repositories.BuyRepository
import org.springframework.stereotype.Service

@Service
class BuyService(
    private val cR : BuyRepository
) {

    fun save(buy : Buy) = cR.save(buy)

}