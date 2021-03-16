package com.salesianostriana.dam.artSpace.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*
import javax.persistence.*
import org.springframework.security.core.userdetails.UserDetails


@Entity
class User(
    private var username: String,
    private var password: String,
    var fullname: String,
    var email: String,
    var address: String,
    var location: String,
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

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: MutableSet<String> = HashSet(),
    private val nonExpired: Boolean = true,
    private val nonLocked: Boolean = true,
    private val enabled : Boolean=true,
    private val credentialsNonExpired : Boolean = true,

    @Id @GeneratedValue var id: UUID? = null
): UserDetails {


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

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableSet()

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username

    override fun isAccountNonExpired(): Boolean = this.nonExpired

    override fun isAccountNonLocked(): Boolean = this.nonLocked

    override fun isCredentialsNonExpired(): Boolean = this.credentialsNonExpired

    override fun isEnabled(): Boolean  = this.enabled

    fun toUserRespDTO() = UserRespDTO(this.username,this.fullname,this.email,this.id)

}