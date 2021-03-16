package com.salesianostriana.dam.ArtSpace.models

import java.util.*

data class UserDTO(
    var username : String,
    var email : String,
    var address : String,
    var location : String,
    var posts: MutableList<Post>?,
    var following : MutableList<User>?,
    var id : UUID?
)

data class UserRegDTO(
    var username: String,
    var email: String,
    var password : String,
    var address: String,
    var location: String,
    var id : UUID?
)

data class UserLogDTO(
    var username: String,
    var email: String,
)