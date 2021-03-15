package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.*

@Entity
class Post (
    var tittle : String,
    var price : Double,
    @Lob var description : String,
    var material : String,
    //Asociacion con User composicion
    @ManyToOne
    var user : User?,

    //Asociacion likes con User
    @ManyToMany(mappedBy = "likes")
    var likesGotten : MutableList<User> = mutableListOf(),

    //Asociacion con ImagePost composicion
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL])
    var images: MutableList<ImagePost> = mutableListOf(),

    //Asociacion con Comment composicion
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf(),


    @Id @GeneratedValue var id : UUID
    ){

}