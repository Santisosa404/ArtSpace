package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.ArtWork
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.ArtWorkService
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

    @GetMapping("/")
    fun getAllArtworks(@AuthenticationPrincipal user: User): ResponseEntity<Any> {
        //Voy a tener que buscar la gente a la que sigue y de la gente a la que sigue sacar sus publicaciones.
        var artworks = artS.allFollowingArtWorks(user.id!!)
        return ResponseEntity.ok().body(artworks.map { it.toListDTO(user) })
    }

}