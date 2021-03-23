package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.models.BuyDetails
import com.salesianostriana.dam.artSpace.repositories.BuyDetailsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BuyDetailsService(
    private val buyDR : BuyDetailsRepository
) {
    fun save(buyDetails: BuyDetails) = buyDR.save(buyDetails)

    fun artWorksToBuyDetails(artWorkMutableList: MutableList<ArtWork>) : MutableList<BuyDetails>{
        var res = mutableListOf<BuyDetails>()
        artWorkMutableList.forEach {
            res.add(BuyDetails(it,it.price))
        }
        return res
    }

    fun saveAll(buyDetailsList: MutableList<BuyDetails>) = buyDR.saveAll(buyDetailsList)
}