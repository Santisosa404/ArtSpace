package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.models.User
import com.salesianostriana.dam.artSpace.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/follow")
class FollowController(
    private val uS : UserService
) {

    @PostMapping("/{id}")
    fun followUser(@PathVariable id : UUID, @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
        return if(uS.existById(id)) {
            var userToFollow = uS.findById(id).get()
            user.addFollower(userToFollow)
            uS.save(userToFollow)
            uS.save(user)
            ResponseEntity.ok().build()
        } else{
            ResponseEntity.notFound().build()
        }
    }
    @DeleteMapping("/{id}")
    fun unfollowUser(@PathVariable id : UUID, @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
        return if(uS.existById(id)) {
            var userToFollow = uS.findById(id).get()
            user.deleteFollower(userToFollow)
            uS.save(user)
            uS.save(userToFollow)
            ResponseEntity.noContent().build()
        } else{
            ResponseEntity.notFound().build()
        }
    }


}