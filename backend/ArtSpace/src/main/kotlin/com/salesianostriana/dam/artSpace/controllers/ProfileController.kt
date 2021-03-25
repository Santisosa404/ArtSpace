package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.SingleEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.models.UserDTO
import com.salesianostriana.dam.artSpace.models.UserEditDTO
import com.salesianostriana.dam.artSpace.services.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
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

    @ApiOperation(value = "Devuelve el perfil de usuario autenticado")
    @GetMapping
    fun profileDet(@ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<Optional<UserDTO>> {
        return ResponseEntity.status(200).body(uS.findById(user.id!!).map { it.toUserDTO() })
    }
    @ApiOperation(value = "Devuelve el usuario a partir de su id")
    @GetMapping("/{id}")
    fun profileId(@ApiParam(value = "Id del usuario a buscar",required = true, type = "UUID") @PathVariable id : UUID): ResponseEntity<UserDTO> {
        return ResponseEntity.status(200).body(uS.findById(id).map { it.toUserDTO() }.orElseThrow { SingleEntityNotFoundException(id.toString(),User::class.java)  })
    }
    @ApiOperation("Edita al usuario actual")
    @PutMapping("/")
    fun profileEdit(@ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User, @ApiParam(value = "Usuario editado", required = true,type = "UserEditDTO") @Valid @RequestBody userEdit :  UserEditDTO) : ResponseEntity<Any> {
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
    @ApiOperation(value = "Devuelve todos lo usuarios")
    @GetMapping("/all")
    fun listAllProfiles(): ResponseEntity<List<UserDTO>> {
        return ResponseEntity.ok().body(uS.findAll().map { it.toUserDTO() })
    }
}