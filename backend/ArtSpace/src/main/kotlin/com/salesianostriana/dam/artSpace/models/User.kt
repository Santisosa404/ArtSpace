package com.salesianostriana.dam.artSpace.models

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
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

    @ElementCollection(fetch = FetchType.EAGER)
    val roles: MutableSet<String> = HashSet(),

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    var actualCart : MutableList<UUID>? = mutableListOf(),
    //Asociacion con ArtWork composicion
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    @LazyCollection(LazyCollectionOption.FALSE)
    var artWorks: MutableList<ArtWork>? = mutableListOf(),

    //Asociacion likes con ArtWork
    @ManyToMany
    @JoinTable(
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "post_id")]
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    var likes: MutableList<ArtWork> = mutableListOf(),


    //Asociacion con  follow usuario
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    var following: MutableList<User> = mutableListOf(),

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    var followers: MutableList<User> = mutableListOf(),


    //Asociacion con Buy
    @OneToMany(mappedBy = "userOrder")
    @LazyCollection(LazyCollectionOption.FALSE)
    var buys: MutableList<Buy> = mutableListOf(),


    private val nonExpired: Boolean = true,
    private val nonLocked: Boolean = true,
    private val enabled: Boolean = true,
    private val credentialsNonExpired: Boolean = true,

    @Id @GeneratedValue var id: UUID? = null
) : UserDetails {

    fun setPassword(pass: String) {
        this.password = pass
    }

    fun setUsername(username: String) {
        this.username = username
    }

    /**
     * Metodos auxiliares  ArtWork
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
        if (!likes.contains(artWork)) {
            likes.add(artWork)
            artWork.likesGotten.add(this)
        }
    }

    fun deleteLike(artWork: ArtWork) {
        if (likes.contains(artWork)) {
            likes.remove(artWork)
            artWork.likesGotten.remove(this)
        }
    }

    /**
     * Metodos auxiliares following
     *
     */
    fun addFollower(user: User) {
        user.followers.add(this)
        this.following.add(user)
    }


    fun deleteFollower(user: User) {
        this.following.remove(user)
        user.followers.remove(this)
    }

    /**
     * Metodos auxiliares carrito
     */
    fun addCart(buy: Buy) {
        buy.userOrder = this
        this.buys.add(buy)
    }

    fun deleteCart(buy: Buy) {
        buy.userOrder = null
        this.buys.remove(buy)
    }


    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableSet()

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username

    override fun isAccountNonExpired(): Boolean = this.nonExpired

    override fun isAccountNonLocked(): Boolean = this.nonLocked

    override fun isCredentialsNonExpired(): Boolean = this.credentialsNonExpired

    override fun isEnabled(): Boolean = this.enabled

    fun toUserRespDTO() = UserRespDTO(this.username, this.fullname, this.email, this.id)

    fun toUserDTO() = UserDTO(
        this.username,
        this.email,
        this.address,
        this.location,
        this.artWorks?.map { it.toDTO() } as MutableList<ArtWorkDTO>,
        this.following.map { it.toUserRespDTO() } as MutableList<UserRespDTO>,
        this.id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }




}