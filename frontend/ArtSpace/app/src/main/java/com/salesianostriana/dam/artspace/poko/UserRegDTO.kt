package com.salesianostriana.dam.artspace.poko

import java.util.*

data class UserRegDTO(
    var username: String,
    var fullname: String,
    var email: String,
    var password: String,
    var address: String,
    var location: String,
    var id: UUID?
)