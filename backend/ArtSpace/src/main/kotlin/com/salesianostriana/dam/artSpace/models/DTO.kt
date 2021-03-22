package com.salesianostriana.dam.artSpace.models

import com.salesianostriana.dam.artSpace.upload.ImgurImageAttribute
import java.util.*
import javax.persistence.Lob
import javax.validation.constraints.*

data class UserDTO(
        @get:NotBlank(message = "{user.username.notBlank}")
        var username : String,
        @get:Email(message = "{user.email.notEmail}")
        var email : String,
        @get:NotBlank(message = "{user.address.notBlank}")
        var address : String,
        @get:NotBlank(message = "{user.location.notBlank}")
        var location : String,
        var description: String,
        var artWorks: MutableList<ArtWorkDTO>?,
        var following : MutableList<UserRespDTO>?,
        var id : UUID?
)

data class UserRegDTO(
    @get:NotBlank(message = "{user.username.notBlank}")
    var username: String,
    @get:NotBlank(message = "{user.fullname.notBlank}")
    var fullname : String,
    @get:Email(message = "{user.email.notEmail}")
    var email: String,
    var password : String,
    @get:NotBlank(message = "{user.address.notBlank}")
    var address: String,
    @get:NotBlank(message = "{user.location.notBlank}")
    var location: String,
    var id : UUID?
)

data class UserLogDTO(
    @get:NotBlank(message = "{user.username.notBlank}")
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

data class UserEditDTO(
    @get:NotBlank(message = "{user.username.notBlank}")
    var username: String,
    @get:NotBlank(message = "{user.fullname.notBlank}")
    var fullname : String,
    @get:Email(message = "{user.email.notEmail}")
    var email: String,
    var password : String,
    @get:NotBlank(message = "{user.address.notBlank}")
    var address: String,
    @get:NotBlank(message = "{user.location.notBlank}")
    var location: String,
    var description : String
)

data class ArtWorkNewDTO(
    @get:NotBlank(message = "{artWork.tittle.notBlank}")
    var tittle : String,
    @Min(value = 1)
    var price : Double,
    var description: String,
    @get:NotBlank(message = "{artWork.material.notBlank}")
    var material : String,
    )

data class ArtWorkEditDTO(
    @get:NotBlank(message = "{artWork.tittle.notBlank}")
    var tittle : String,
    @Min(value = 1)
    var price : Double,
    var description: String,
    @get:NotBlank(message = "{artWork.material.notBlank}")
    var material : String
)

data class ArtWorkDTO(
    @get:NotBlank(message = "{artWork.tittle.notBlank}")
    var tittle : String,
    @Min(value = 1)
    var price : Double,
    var description: String,
    @get:NotBlank(message = "{artWork.material.notBlank}")
    var material : String,
    var images : MutableList<ImageArtWorkDTO>?,
    var likes : MutableList<UserRespDTO>,
    var comments : MutableList<CommentDTO>,
    var id: UUID?
    )
data class ArtWorkCartDTO(
    @get:NotBlank(message = "{artWork.tittle.notBlank}")
    var tittle : String,
    @Min(value = 1)
    var price : Double,
    @Lob var description: String,
    @get:NotBlank(message = "{artWork.material.notBlank}")
    var material : String,
    var images : MutableList<ImageArtWorkDTO>? = mutableListOf(),
    var id: UUID?
    )

data class ImageArtWorkDTO(
    var img : ImgurImageAttribute?,
    var id: UUID?
)

data class  CommentDTO(
    @get:NotNull(message = "{comment.body.notNull}")
    @Lob var body : String
)

data class CartDTO(
    var orders : MutableList<ArtWorkCartDTO>,
    var finalPrice : Double,
    var id: UUID?=null
)