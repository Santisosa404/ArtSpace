package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.PostRemove

@RequestMapping("/like")
@RestController
class LikeController(
    private val artS : ArtWorkService,
    private val userS : UserService
) {
    @ApiOperation(value = "Da like a una publicacion")
    @PostMapping("/{id}")
    fun likeArtWork(@ApiParam(value = "Id del artwork a dar like",required = true, type = "UUID")@PathVariable id : UUID, @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        var artWork = artS.findById(id).get()

        user.addLike(artWork)
        artS.save(artWork)
        userS.save(user)
        return ResponseEntity.status(200).build()
    }
    @ApiOperation(value = "Elimina el like de una publicacion")
    @DeleteMapping("/{id}")
    fun dislikeArtWork(@ApiParam(value = "Id del artwork a dar dislike",required = true, type = "UUID")@PathVariable id : UUID, @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        var artWork = artS.findById(id).get()
        user.deleteLike(artWork)
        artS.save(artWork)
        userS.save(user)
        return ResponseEntity.status(200).build()
    }




}