package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class ImageArtWork(
        var dataId: String,
        var deleteHash: String,

        //Asociacion artWork composicion
        @ManyToOne
        var artWork: ArtWork?,

        @Id @GeneratedValue var id: UUID?=null
) {
}