package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class OrderDetails(
        var price : Double,

        //Asociacion con ArtWork
        @OneToMany(mappedBy = "orderDet")
        var orderArtWork: MutableList<ArtWork> = mutableListOf(),

        @Id @GeneratedValue var id : UUID?=null
) {

    /**
     * Metodos auxiliares order det
     */
    fun addArtWork(artWork: ArtWork){
        artWork.orderDet=this
        orderArtWork.add(artWork)
    }

}