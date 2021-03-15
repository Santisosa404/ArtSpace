package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class ImagePost(
    var dataId : String,
    var deleteHash : String,
    @Id @GeneratedValue var id : UUID
) {
}