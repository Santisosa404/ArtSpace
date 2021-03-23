package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*
import javax.validation.constraints.*
@Entity
class ArtWork(
    @field:NotBlank(message="{artWork.tittle.notBlank}")
    var tittle: String,
    @get:Min(value=1)
    var price: Double,
    @Lob var description: String,
    @get:NotBlank(message = "{artWork.material.notBlank}")
    var material: String,
    //Asociacion con User composicion
    @ManyToOne
    var user: User?,

    //Asociacion likes con User
    @ManyToMany(mappedBy = "likes", cascade = [CascadeType.ALL])
    var likesGotten: MutableList<User> = mutableListOf(),

    //Asociacion con ImageArtWorkRepository composicion
    @OneToMany(mappedBy = "artWork", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var images: MutableList<ImageArtWork> = mutableListOf(),

    //Asociacion con Comment composicion
    @OneToMany(mappedBy = "artWork", cascade = [CascadeType.ALL])
    var comments: MutableList<Comment> = mutableListOf(),

    //Asociacion con OrderDetails
    @OneToOne(mappedBy = "artWork")
    var buyLine: BuyDetails? = null,


    @Id @GeneratedValue var id: UUID? = null
) {
    /**
     * Metodos auxiliaes images
     */
    fun addImg(imageArtWork: ImageArtWork) {
        imageArtWork.artWork = this
        this.images.add(imageArtWork)
    }

    fun deleteImg(imageArtWork: ImageArtWork) {
        imageArtWork.artWork = null
        this.images.remove(imageArtWork)
    }

    /**
     * Metodos auxiliares comment
     */
    fun addComment(comment: Comment) {
        comment.artWork = this
        this.comments.add(comment)
    }

    fun deleteComment(comment: Comment) {
        comment.artWork = null
        this.comments.remove(comment)
    }

    fun toNewDTO() = ArtWorkNewDTO(this.tittle, this.price, this.description, this.material)
    fun toDTO() = ArtWorkDTO(
        this.tittle,
        this.price,
        this.description,
        this.material,
        this.images.map { it.toDTO() } as MutableList<ImageArtWorkDTO>,
        this.likesGotten.map { it.toUserRespDTO() } as MutableList<UserRespDTO>,
        this.comments.map { it.toDTO() } as MutableList,
        this.user!!.username,
        this.id)

    fun toArtWorkCartDTO() = ArtWorkCartDTO(this.tittle,
        this.price,
        this.description,
        this.material,
        this.images.map { it.toDTO() } as MutableList<ImageArtWorkDTO>,
        this.id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArtWork

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }


}