package com.salesianostriana.dam.artSpace.models

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.*
import javax.persistence.*

@Entity
class Cart(
    var finalPrice: Double?=0.0,

    //Asociacion con OrderDetails
    @OneToMany(mappedBy = "orderInDetails", cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    var ordering: MutableList<CartDetails> = mutableListOf(),


    //Asociacion con User
    @ManyToOne
    var userOrder: User? = null,

    @Id @GeneratedValue var id: UUID? = null
) {
    /**
     * Metodos auxiliares orderIndetails
     */
    fun addOrder(cartDetails: CartDetails) {
        cartDetails.orderInDetails = this
        this.ordering.add(cartDetails)
    }

    fun deleteOrder(cartDetails: CartDetails) {
        cartDetails.orderInDetails = null
        this.ordering.remove(cartDetails)
    }

    fun setPrecio(){
        var res =0.0
        if (this.ordering.isNotEmpty())
            this.ordering.forEach { res+= it.price!!}
        this.finalPrice = res
    }

}