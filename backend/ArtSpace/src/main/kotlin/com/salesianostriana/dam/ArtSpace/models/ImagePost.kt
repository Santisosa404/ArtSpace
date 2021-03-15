package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class ImagePost(
    var dataId : String,
    var deleteHash : String,

    //Asociacion post composicion
    @ManyToOne
    var post : Post?,

    @Id @GeneratedValue var id : UUID
) {
}