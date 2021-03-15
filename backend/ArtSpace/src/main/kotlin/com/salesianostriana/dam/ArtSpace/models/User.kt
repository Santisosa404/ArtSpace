package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    private var username : String,
    private var password : String,
    private var email : String,
    private var address : String,
    private var location : String,
    private var description : String,
    @Id @GeneratedValue private var id : UUID
) {
}