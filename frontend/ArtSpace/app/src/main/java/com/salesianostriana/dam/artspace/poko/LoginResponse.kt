package com.salesianostriana.dam.artspace.poko

data class LoginResponse(
    val refreshToken: String,
    val token: String,
    val user: RegisterRequest
)