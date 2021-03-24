package com.salesianostriana.dam.artspace.poko

import java.util.*


data class UserDTO(
    var username: String,
    var email: String,
    var address: String,
    var location: String,
    var artWorks: MutableList<ArtWorkDTO>?,
    var following: MutableList<UserRespDTO>?,
    var id: UUID?
)

data class UserRegDTO(
    var username: String,
    var fullname: String,
    var email: String,
    var password: String,
    var address: String,
    var location: String,
    var id: UUID?
)

data class UserLogDTO(
    var username: String,
    var password: String
)

data class UserTokenDTO(
    val token: String,
    var refreshToken: String,
    var user: UserRespDTO
)

data class UserRespDTO(
    var username: String,
    var fullname: String,
    var email: String,
    var id: UUID?
)

data class UserEditDTO(
    var username: String,
    var fullname: String,
    var email: String,
    var password: String,
    var address: String,
    var location: String,
    var description: String
)

data class ArtWorkNewDTO(
    var tittle: String,
    var price: Double,
    var description: String,
    var material: String
)

data class ArtWorkEditDTO(
    var tittle: String,
    var price: Double,
    var description: String,
    var material: String
)

data class ArtWorkDTO(
    var tittle : String,
    var price : Double,
    var description: String,
    var material : String,
    var images : MutableList<ImageArtWorkDTO>?,
    var likes : MutableList<UserRespDTO>,
    var comments : MutableList<CommentDTO>,
    var username : String? = null,
    var userId : UUID? = null,
    var id: UUID?
)

data class ArtWorkCartDTO(
    var tittle: String,
    var price: Double,
    var description: String,
    var material: String,
    var images: MutableList<ImageArtWorkDTO>? = mutableListOf(),
    var id: UUID?
)
data class ArtWorkListDTO(
    var tittle : String,
    var price : Double,
    var description: String,
    var material : String,
    var images : MutableList<ImageArtWorkDTO>?,
    var likes : MutableList<UserRespDTO>,
    var comments : MutableList<CommentDTO>,
    var userName : String? = null,
    var userId : UUID?=null,
    var meGustaUsuario:Boolean,
    var id: UUID?
)

data class ImageArtWorkDTO(
    var img: String?,
    var id: UUID?
)

data class ImgurImageAttribute(var id: String?, var deletehash: String?)

data class CommentDTO(
    var body: String
)

data class CartDTO(
    var orders: MutableList<ArtWorkCartDTO>,
    var finalPrice: Double,
    var id: UUID? = null
)