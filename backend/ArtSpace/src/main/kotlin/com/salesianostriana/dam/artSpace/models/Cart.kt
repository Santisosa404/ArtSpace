package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*

@Entity
class Cart(
        var finalPrice : Double,

        //Asociacion con OrderDetails
        @OneToMany(mappedBy = "orderInDetails", cascade = [CascadeType.ALL])
        var ordering : MutableList<CartDetails> = mutableListOf(),


        @Id @GeneratedValue var id : UUID?=null
) {
}