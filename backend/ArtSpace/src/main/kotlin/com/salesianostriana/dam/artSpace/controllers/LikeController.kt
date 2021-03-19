package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.UserService
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

    @PostMapping("/{id}")
    fun likeArtWork(@PathVariable id : UUID, @AuthenticationPrincipal user: User): ResponseEntity.BodyBuilder {
        var artWork = artS.findById(id).get()
        user.addLike(artWork)
        artS.save(artWork)
        userS.save(user)
        return ResponseEntity.status(HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{id}")
    fun dislikeArtWork(@PathVariable id : UUID, @AuthenticationPrincipal user: User): ResponseEntity.BodyBuilder {
        var artWork = artS.findById(id).get()
        user.deleteLike(artWork)
        userS.save(user)
        artS.save(artWork)
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
    }




}