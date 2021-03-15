package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Comment(
    @Lob var body : String,
    @Id @GeneratedValue  var id : UUID
) {
}