package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.ListEntityNotFoundException
import com.salesianostriana.dam.artSpace.exceptions.SingleEntityNotFoundException
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
            var userToFollow = uS.findById(id).orElseThrow{ SingleEntityNotFoundException(id.toString(),User::class.java)}
            user.addFollower(userToFollow)
            uS.save(userToFollow)
            uS.save(user)
          return  ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun unfollowUser(@PathVariable id : UUID, @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
            var userToFollow = uS.findById(id).orElseThrow { SingleEntityNotFoundException(id.toString(),User::class.java) }
            user.deleteFollower(userToFollow)
            uS.save(user)
            uS.save(userToFollow)
          return  ResponseEntity.noContent().build()
    }


}