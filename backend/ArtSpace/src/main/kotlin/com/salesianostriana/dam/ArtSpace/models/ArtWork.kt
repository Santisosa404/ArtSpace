package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.*

@Entity
class ArtWork(
        var tittle: String,
        var price: Double,
        @Lob var description: String,
        var material: String,
        //Asociacion con User composicion
        @ManyToOne
        var user: User?,

        //Asociacion likes con User
        @ManyToMany(mappedBy = "likes")
        var likesGotten: MutableList<User> = mutableListOf(),

        //Asociacion con ImageArtWork composicion
        @OneToMany(mappedBy = "artWork", cascade = [CascadeType.ALL])
        var images: MutableList<ImageArtWork> = mutableListOf(),

        //Asociacion con Comment composicion
        @OneToMany(mappedBy = "artWork", cascade = [CascadeType.ALL])
        var comments: MutableList<Comment> = mutableListOf(),

        //Asociacion con OrderDetails
        @ManyToOne
        var orderDet : OrderDetails? = null,

        @Id @GeneratedValue var id: UUID?=null
) {

}