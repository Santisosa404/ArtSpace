package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Lob
@Entity
class Post (
    var tittle : String,
    var price : Double,
    @Lob var description : String,
    var material : String,
    @Id @GeneratedValue var id : UUID
    ){

}