package com.salesianostriana.dam.artspace.retrofit

import com.salesianostriana.dam.artspace.poko.CartDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface CartService {

    @POST("/cart/{id}")
    fun addToCart(@Header("Authorization") token : String, @Path("id") id : UUID) : Call<Void>

    @GET("/cart/")
    fun getCart(@Header("Authorization") token: String) : Call<CartDTO>

    @POST("/cart/buy")
    fun buyCart(@Header("Authorization") token: String) : Call<Void>
}