package com.salesianostriana.dam.artSpace.models

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
        @ManyToMany(mappedBy = "likes",cascade = [CascadeType.ALL])
        var likesGotten: MutableList<User> = mutableListOf(),

        //Asociacion con ImageArtWorkRepository composicion
        @OneToMany(mappedBy = "artWork", cascade = [CascadeType.ALL],fetch = FetchType.EAGER)
        var images: MutableList<ImageArtWork> = mutableListOf(),

        //Asociacion con Comment composicion
        @OneToMany(mappedBy = "artWork", cascade = [CascadeType.ALL])
        var comments: MutableList<Comment> = mutableListOf(),

        //Asociacion con OrderDetails
        @ManyToOne
        var orderDet : CartDetails? = null,


        @Id @GeneratedValue var id: UUID?=null
) {
        /**
         * Metodos helper images
         */
        fun addImg(imageArtWork: ImageArtWork){
                imageArtWork.artWork=this
                this.images.add(imageArtWork)
        }
        fun deleteImg(imageArtWork: ImageArtWork){
                imageArtWork.artWork = null
                this.images.remove(imageArtWork)
        }
        fun toNewDTO() = ArtWorkNewDTO(this.tittle,this.price,this.description,this.material)
        fun toDTO() = ArtWorkDTO(this.tittle,this.price,this.description,this.material,this.images.map { it.toDTO() } as MutableList<ImageArtWorkDTO>,this.likesGotten.map { it.toUserRespDTO() } as MutableList<UserRespDTO>,this.id)

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as ArtWork

                if (id != other.id) return false

                return true
        }

        override fun hashCode(): Int {
                return id?.hashCode() ?: 0
        }


}