package com.salesianostriana.dam.artSpace.repositories

import com.salesianostriana.dam.artSpace.models.BuyDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BuyDetailsRepository : JpaRepository<BuyDetails, UUID> {

}