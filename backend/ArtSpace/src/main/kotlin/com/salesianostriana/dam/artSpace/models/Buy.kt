package com.salesianostriana.dam.artSpace.models

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.*
import javax.persistence.*


@Entity
class Buy(
    var finalPrice: Double?=0.0,

    //Asociacion con OrderDetails
    @OneToMany(mappedBy = "orderInDetails", cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    var ordering: MutableList<BuyDetails> = mutableListOf(),


    //Asociacion con User
    @ManyToOne
    var userOrder: User? = null,

    @Id @GeneratedValue var id: UUID? = null
) {
    /**
     * Metodos auxiliares orderIndetails
     */
    fun addOrder(buyDetails: BuyDetails) {
        buyDetails.orderInDetails = this
        this.ordering.add(buyDetails)
    }

    fun deleteOrder(buyDetails: BuyDetails) {
        buyDetails.orderInDetails = null
        this.ordering.remove(buyDetails)
    }

    fun setPrecio(){
        var res =0.0
        if (this.ordering.isNotEmpty())
            this.ordering.forEach { res+= it.price!!}
        this.finalPrice = res
    }

}