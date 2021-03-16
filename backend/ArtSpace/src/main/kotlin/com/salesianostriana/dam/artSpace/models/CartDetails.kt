package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*

@Entity
class CartDetails(
        var price : Double,

        //Asociacion con ArtWork
        @OneToMany(mappedBy = "orderDet")
        var orderArtWork: MutableList<ArtWork> = mutableListOf(),


        //Asociacion con Order compuesta
        @ManyToOne
        var orderInDetails: Cart,


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