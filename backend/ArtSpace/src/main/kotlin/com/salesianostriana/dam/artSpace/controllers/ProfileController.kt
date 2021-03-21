package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.models.UserDTO
import com.salesianostriana.dam.artSpace.models.UserEditDTO
import com.salesianostriana.dam.artSpace.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

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
    fun profileId(@PathVariable id : UUID): ResponseEntity<Optional<UserDTO>> {
        return if (uS.existById(id))
            ResponseEntity.status(200).body(uS.findById(id).map { it.toUserDTO() })
        else
            ResponseEntity.notFound().build()
    }

    @PutMapping("/")
    fun profileEdit(@AuthenticationPrincipal user: User, @RequestBody userEdit :  UserEditDTO) : ResponseEntity<Any> {
        return if (uS.existById(user.id!!)){
            uS.findById(user.id!!).map {
                it.username = userEdit.username
                it.password = passEnc.encode(userEdit.password)
                it.fullname = userEdit.fullname
                it.email = userEdit.email
                it.address = userEdit.address
                it.location = userEdit.location
                it.description = userEdit.description
                uS.save(it)
            }
            //TODO
            ResponseEntity.status(204).build()
        }else{
           ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/all")
    fun listAllProfiles(): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok().body(uS.findAll().map { it.toUserDTO() })
    }
}