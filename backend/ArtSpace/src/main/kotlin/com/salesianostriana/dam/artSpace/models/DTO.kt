package com.salesianostriana.dam.artSpace.models

import java.util.*

data class UserDTO(
        var username : String,
        var email : String,
        var address : String,
        var location : String,
        var artWorks: MutableList<ArtWork>?,
        var following : MutableList<User>?,
        var id : UUID?
)

data class UserRegDTO(
    var username: String,
    var fullname : String,
    var email: String,
    var password : String,
    var address: String,
    var location: String,
    var id : UUID?
)

data class UserLogDTO(
    var username: String,
    var password: String,
)
data class UserTokenDTO(
    val token : String,
    var refreshToken: String,
    var user: UserRespDTO,
)
data class UserRespDTO(
    var username: String,
    var fullname : String,
    var email: String,
    var id : UUID?
)