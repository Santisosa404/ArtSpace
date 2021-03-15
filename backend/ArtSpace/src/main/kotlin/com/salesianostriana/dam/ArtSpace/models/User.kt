package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class User(
    private var username : String,
    private var password : String,
    var email : String,
    var address : String,
    var location : String,
    @Lob var description : String,
    @Id @GeneratedValue var id : UUID
) {
}