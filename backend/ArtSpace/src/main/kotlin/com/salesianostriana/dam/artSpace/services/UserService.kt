package com.salesianostriana.dam.artSpace.services

import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.models.UserRegDTO
import com.salesianostriana.dam.artSpace.repositories.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val uR : UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun findByUsername(username : String) = uR.findByUsername(username)

    fun findById(id : UUID) = uR.findById(id)

    fun createUser(userRegDTO: UserRegDTO): Optional<User> { if (findByUsername(userRegDTO.username).isPresent)
        return Optional.empty<User>()
    return Optional.of(
    with(userRegDTO){
        uR.save(User(this.username,passwordEncoder.encode(this.password),this.fullname,this.email,this.address,this.location,"",mutableSetOf("USER")))
    }
    )
        }

    fun existById(id : UUID) = uR.existsById(id)

    fun save(user: User) = uR.save(user)
    fun findAll() = uR.findAll()
}