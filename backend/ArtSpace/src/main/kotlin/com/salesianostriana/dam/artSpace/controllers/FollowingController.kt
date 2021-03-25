package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/following")
class FollowingController(
    private val artS : ArtWorkService
) {
    @ApiOperation(value = "Devuelve todos los Artwork de aquellos usuarios a los que sigues")
    @GetMapping("/")
    fun getAllArtworks(@ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        var artworks = artS.allFollowingArtWorks(user.id!!)
        return ResponseEntity.ok().body(artworks.map { it.toListDTO(user) })
    }

}