package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.ArtWorkDTO
import com.salesianostriana.dam.artSpace.models.ArtWorkListDTO
import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/trending")
class TrendingTopicsController (
    private val artS : ArtWorkService
        ){

    @GetMapping("/")
    fun getNotFollowing(@AuthenticationPrincipal user: User): ResponseEntity<List<ArtWorkDTO>> {
        var artWorks = artS.allNotFollowingArtsWorks(user.following,user.id!!)
        return ResponseEntity.ok().body(artWorks.map { it.toDTO() })
    }
}