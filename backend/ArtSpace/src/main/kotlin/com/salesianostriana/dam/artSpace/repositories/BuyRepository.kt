package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.Buy
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface BuyRepository : JpaRepository<Buy, UUID> {

}