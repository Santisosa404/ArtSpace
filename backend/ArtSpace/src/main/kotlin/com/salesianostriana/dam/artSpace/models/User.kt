package com.salesianostriana.dam.artSpace.models

import java.util.*
import javax.persistence.*

@Entity
class User(
    private var username: String,
    private var password: String,
    var email: String,
    var address: String,
    var location: String,
    var fullname: String,
    @Lob var description: String,

        //Asociacion con ArtWork composicion
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
        var artWorks: MutableList<ArtWork>? = mutableListOf(),

        //Asociacion likes con ArtWork
    @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(
                joinColumns = [JoinColumn(name = "user_id")],
                inverseJoinColumns = [JoinColumn(name = "post_id")]
        )
        var likes: MutableList<ArtWork> = mutableListOf(),


        //Asociacion con  follow usuario
    @ManyToMany
        var following: MutableList<User> = mutableListOf(),

        //Asociacion con Cart
    @OneToMany(mappedBy = "userOrder")
    var carts : MutableList<Cart> = mutableListOf(),

    @Id @GeneratedValue var id: UUID? = null
) {


    /**
     * Metodos auxiliares composicion User -> ArtWork
     */
    fun addPost(artWork: ArtWork) {
        artWork.user = this
        this.artWorks?.add(artWork)
    }

    fun removePost(artWork: ArtWork) {
        this.artWorks?.remove(artWork)
        artWork.user = null
    }

    /**
     * Metodos auxiliares  likes
     */
    fun addLike(artWork: ArtWork) {
        likes.add(artWork)
        artWork.likesGotten.add(this)
    }

    fun deleteLike(artWork: ArtWork) {
        likes.remove(artWork)
        artWork.likesGotten.remove(this)
    }


}