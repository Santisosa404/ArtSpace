package com.salesianostriana.dam.artSpace.controllers

import com.salesianostriana.dam.artSpace.exceptions.ListEntityNotFoundException
import com.salesianostriana.dam.artSpace.models.*
import com.salesianostriana.dam.artSpace.services.ArtWorkService
import com.salesianostriana.dam.artSpace.services.BuyDetailsService
import com.salesianostriana.dam.artSpace.services.BuyService
import com.salesianostriana.dam.artSpace.services.UserService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/cart")
@RestController
class CartController(
    private val cS: BuyService,
    private val buyDS: BuyDetailsService,
    private val buyS : BuyService,
    private val artS: ArtWorkService,
    private val uS: UserService
) {
    @ApiOperation(value = "Agrega una id del Artwork al carrito actual de usuario autenticado")
    @PostMapping("/{id}")
    fun addToCart(@ApiParam(value = "Id del artwork a agregar",required = true, type = "UUID") @PathVariable id: UUID, @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        user.actualCart?.add(id)
        uS.save(user)
        return ResponseEntity.status(200).build()
    }
    @ApiOperation(value = "Elimina una id del Artwork agregado al carrito actual de usuario autenticado")
    @DeleteMapping("/{id}")
    fun deleteFromCart(@ApiParam(value = "Id del artwork a eliminar",required = true, type = "UUID")  @PathVariable id: UUID, @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<Any> {
        return if (artS.existById(id)) {
            user.actualCart?.remove(id)
            uS.save(user)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
    @ApiOperation(value = "Lista los productos que se encuentran en el carrito")
    @GetMapping("/")
    fun getCart(@ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User): ResponseEntity<CartDTO> {
        return if (user.actualCart!!.isNotEmpty()) {
            var res = artS.allArtWorkById(user.actualCart!!)
            ResponseEntity.status(HttpStatus.OK).body(CartDTO(res.map { it.toArtWorkCartDTO() } as MutableList<ArtWorkCartDTO>,artS.price(res)))
        }
        else
            ResponseEntity.status(200).body(CartDTO(mutableListOf(),0.0))
    }
    @ApiOperation(value = "Realiza la compra de los productos actuales del carrito")
    @PostMapping("/buy")
    fun buyCart( @ApiParam(value = "Usuario registrado actualmente", required = true,type = "User") @AuthenticationPrincipal user: User) : ResponseEntity<Any>{
        return if(user.actualCart!!.isNotEmpty()){
            var artWorkList = artS.allArtWorkById(user.actualCart!!)
            var buyDetailsList = buyDS.artWorksToBuyDetails(artWorkList)
            var buy = Buy(artS.price(artWorkList), buyDetailsList)
            user.addCart(buy)
            buyDS.saveAll(buyDetailsList)
            buyS.save(buy)
            user.actualCart!!.clear()
            uS.save(user)
            ResponseEntity.status(HttpStatus.ACCEPTED).build()
        }else
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }


}