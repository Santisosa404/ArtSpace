package com.salesianostriana.dam.artSpace.models

import com.salesianostriana.dam.artSpace.upload.ImgurImageAttribute
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class ImageArtWork(
        //Asociacion artWork composicion
        var img : ImgurImageAttribute?=null,

        @ManyToOne
        var artWork: ArtWork? = null,

        @Id @GeneratedValue var id: UUID?=null
) {


        fun toDTO() = ImageArtWorkDTO(this.img?.id,this.id)
}