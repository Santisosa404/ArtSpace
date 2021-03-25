package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.SingleEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.models.UserDTO
import com.salesianostriana.dam.artSpace.models.UserEditDTO
import com.salesianostriana.dam.artSpace.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/profile")
class ProfileController(
    private val uS : UserService,
    private val passEnc : PasswordEncoder
) {


    @GetMapping
    fun profileDet(@AuthenticationPrincipal user: User): ResponseEntity<Optional<UserDTO>> {
        return ResponseEntity.status(200).body(uS.findById(user.id!!).map { it.toUserDTO() })
    }

    @GetMapping("/{id}")
    fun profileId(@PathVariable id : UUID): ResponseEntity<UserDTO> {
        return ResponseEntity.status(200).body(uS.findById(id).map { it.toUserDTO() }.orElseThrow { SingleEntityNotFoundException(id.toString(),User::class.java)  })
    }

    @PutMapping("/")
    fun profileEdit(@AuthenticationPrincipal user: User, @Valid @RequestBody userEdit :  UserEditDTO) : ResponseEntity<Any> {
            uS.findById(user.id!!).map {
                it.username = userEdit.username
                it.fullname = userEdit.fullname
                it.email = userEdit.email
                it.address = userEdit.address
                it.location = userEdit.location
                it.description = userEdit.description
                uS.save(it)
            }.orElseThrow{
                SingleEntityNotFoundException(user.id!!.toString(),User::class.java)
            }
            return ResponseEntity.status(204).build()
    }

    @GetMapping("/all")
    fun listAllProfiles(): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok().body(uS.findAll().map { it.toUserDTO() })
    }
}