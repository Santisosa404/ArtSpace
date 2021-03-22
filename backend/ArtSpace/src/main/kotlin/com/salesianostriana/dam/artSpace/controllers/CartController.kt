package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.ListEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.CartDetailsService
import com.salesianostriana.dam.artSpace.services.CartService
import com.salesianostriana.dam.artSpace.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/cart")
@RestController
class CartController(
    private val cS: CartService,
    private val cartDS: CartDetailsService,
    private val artS: ArtWorkService,
    private val uS: UserService
) {

    @PostMapping("/{id}")
    fun addToCart(@PathVariable id: UUID, @AuthenticationPrincipal user: User): ResponseEntity<Any> {

            var artWork = artS.findById(id).orElseThrow { ListEntityNotFoundException(ArtWork::class.java) }
            if (user.carts.isNotEmpty()) {
                user.carts.last().ordering.last().addOrderArt(artWork)
                user.carts.last().ordering.last().setPrecio()
                user.carts.last().setPrecio()
                println(user.carts.last().finalPrice)
                cartDS.save(user.carts.last().ordering.last())
                cS.save(user.carts.last())
                uS.save(user)
            } else {
                var cart = Cart()
                var cartDetails = CartDetails()
                cartDetails.addOrderArt(artWork)
                cartDetails.setPrecio()
                cartDS.save(cartDetails)
                cart.addOrder(cartDetails)
                cart.setPrecio()
                cS.save(cart)
                user.addCart(cart)
                uS.save(user)
                cS.save(cart)
                cartDS.save(cartDetails)
                uS.save(user)

            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build()

    }

    @DeleteMapping("/{id}")
    fun deleteFromCart(@PathVariable id: UUID, @AuthenticationPrincipal user: User) : ResponseEntity<Any>{

            var artWork = artS.findById(id).orElseThrow { ListEntityNotFoundException(ArtWork::class.java) }
            user.carts.last().ordering.last().removeOrderArt(artWork)
            user.carts.last().ordering.last().setPrecio()
            user.carts.last().setPrecio()
            cartDS.save(user.carts.last().ordering.last())
            cS.save(user.carts.last())
            uS.save(user)
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build()

    }

    @GetMapping("/")
    fun getCarrito(@AuthenticationPrincipal user: User): ResponseEntity<CartDTO> {
        return if (user.carts.isNotEmpty())
         ResponseEntity.ok().body(CartDTO(user.carts.last().ordering.last().orderArtWork.map { it.toArtWorkCartDTO() } as MutableList,user.carts.last().finalPrice!!,user.carts.last().id))
        else
            ResponseEntity.ok(CartDTO(mutableListOf(),0.0))
    }

}