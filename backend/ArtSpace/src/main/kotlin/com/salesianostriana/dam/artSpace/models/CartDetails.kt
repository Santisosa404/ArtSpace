package com.salesianostriana.dam.artSpace.models

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.*
import javax.persistence.*

@Entity
class CartDetails(
    var price: Double?=0.0,

    //Asociacion con ArtWork
    @OneToMany(mappedBy = "orderDet")
    @LazyCollection(LazyCollectionOption.FALSE)
    var orderArtWork: MutableList<ArtWork> = mutableListOf(),


    //Asociacion con Order compuesta
    @ManyToOne
    var orderInDetails: Cart? = null,


    @Id @GeneratedValue var id: UUID? = null
) {

    /**
     * Metodos auxiliares order det
     */
    fun addOrderArt(artWork: ArtWork) {
        artWork.orderDet = this
        this.orderArtWork.add(artWork)
    }

    fun removeOrderArt(artWork: ArtWork) {
        artWork.orderDet = null
        this.orderArtWork.remove(artWork)
    }

    fun setPrecio(){
        var res =0.0
        if (this.orderArtWork.isNotEmpty())
            this.orderArtWork.forEach { res += it.price }
        this.price = res
    }
}