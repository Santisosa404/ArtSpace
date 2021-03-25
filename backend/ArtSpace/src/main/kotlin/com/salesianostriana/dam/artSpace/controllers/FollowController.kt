package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.ListEntityNotFoundException
import com.salesianostriana.dam.artSpace.exceptions.SingleEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/follow")
class FollowController(
    private val uS : UserService
) {
    @ApiOperation(value = "Agrega al usuario a la lista de seguidores")
    @PostMapping("/{id}")
    fun followUser(@ApiParam(value = "Id del usuario a seguir",required = true, type = "UUID") @PathVariable id : UUID,  @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
            var userToFollow = uS.findById(id).orElseThrow{ SingleEntityNotFoundException(id.toString(),User::class.java)}
            user.addFollower(userToFollow)
            uS.save(userToFollow)
            uS.save(user)
          return  ResponseEntity.ok().build()
    }
    @ApiOperation(value = "Elimina al usuario de la lista de seguidores")
    @DeleteMapping("/{id}")
    fun unfollowUser(@ApiParam(value = "Id del usuario a dejar de seguir",required = true, type = "UUID") @PathVariable id : UUID,  @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
            var userToFollow = uS.findById(id).orElseThrow { SingleEntityNotFoundException(id.toString(),User::class.java) }
            user.deleteFollower(userToFollow)
            uS.save(user)
            uS.save(userToFollow)
          return  ResponseEntity.ok().build()
    }


}