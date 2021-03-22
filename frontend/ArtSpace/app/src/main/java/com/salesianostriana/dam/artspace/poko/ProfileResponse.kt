package com.salesianostriana.dam.artspace.poko

import java.util.*

data class ProfileResponse(
    var username : String?=null,
    var email : String?=null,
    var address : String?=null,
    var location : String?=null,
    var description : String?=null,
    var artWorks: MutableList<ArtWorkDTO>? = mutableListOf(),
    var following : MutableList<UserRespDTO>? = mutableListOf(),
    var id : UUID?=null
)