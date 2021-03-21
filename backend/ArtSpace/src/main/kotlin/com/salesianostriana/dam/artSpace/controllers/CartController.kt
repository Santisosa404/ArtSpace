package com.salesianostriana.dam.artSpace.controllers

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
        if (artS.existById(id)) {
            var artWork = artS.findById(id).get()
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
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteFromCart(@PathVariable id: UUID, @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
        return if (artS.existById(id)){
            var artWork = artS.findById(id).get()
            user.carts.last().ordering.last().removeOrderArt(artWork)
            user.carts.last().ordering.last().setPrecio()
            user.carts.last().setPrecio()
            cartDS.save(user.carts.last().ordering.last())
            cS.save(user.carts.last())
            uS.save(user)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }else{
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @GetMapping("/")
    fun getCarrito(@AuthenticationPrincipal user: User): ResponseEntity<CartDTO> {
        var userOrder = user.carts.last().ordering.last().orderArtWork.map { it.toArtWorkCartDTO() } as MutableList
        var id = user.carts.last().id
        var price = user.carts.last().finalPrice
        return ResponseEntity.ok().body(CartDTO(userOrder,price!!,id))
    }

}