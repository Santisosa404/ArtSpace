package com.salesianostriana.dam.ArtSpace.models

import java.util.*
import javax.persistence.*

@Entity
class User(
    private var username: String,
    private var password: String,
    var email: String,
    var address: String,
    var location: String,
    var fullname : String,
    @Lob var description: String,

    //Asociacion con Post composicion
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var posts: MutableList<Post>? = mutableListOf(),

    //Asociacion likes con Post
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "post_id")]
    )
    var likes : MutableList<Post> = mutableListOf(),


//    //Asociacion con  follow usuario
//    @ManyToMany
//    @JoinTable(
//        joinColumns = [JoinColumn(name = "userFollower")],
//        inverseJoinColumns = [JoinColumn(name = "")]
//    )


    @Id @GeneratedValue var id: UUID
) {


    /**
     * Metodos auxiliares composicion User -> Post
     */
    fun addPost(post: Post) {
        post.user = this
        this.posts?.add(post)
    }

    fun removePost(post: Post) {
        this.posts?.remove(post)
        post.user = null
    }

    /**
     * Metodos auxiliares  likes
     */
    fun addLike(post : Post){
        likes.add(post)
        post.likesGotten.add(this)
    }
    fun deleteLike(post : Post){
        likes.remove(post)
        post.likesGotten.remove(this)
    }


}