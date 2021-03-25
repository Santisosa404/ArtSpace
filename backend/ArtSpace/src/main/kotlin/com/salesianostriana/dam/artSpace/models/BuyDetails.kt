package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*

@Entity
class BuyDetails(


    //Asociacion con ArtWork
    @OneToOne
    var artWork: ArtWork,

    var price: Double?=artWork.price,

    //Asociacion con Order compuesta
    @ManyToOne
    var orderInDetails: Buy? = null,


    @Id @GeneratedValue var id: UUID? = null
) {

}