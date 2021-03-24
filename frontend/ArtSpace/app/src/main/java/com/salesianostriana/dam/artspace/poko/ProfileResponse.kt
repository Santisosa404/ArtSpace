package com.salesianostriana.dam.artspace.poko

import java.util.*

data class ProfileResponse(
    var username : String,
    var fullname : String,
    var email : String,
    var address : String,
    var location : String,
    var description : String,
    var artWorks: MutableList<ArtWorkDTO>? = mutableListOf(),
    var following : MutableList<UserRespDTO>? = mutableListOf(),
    var id : UUID?=null
)
