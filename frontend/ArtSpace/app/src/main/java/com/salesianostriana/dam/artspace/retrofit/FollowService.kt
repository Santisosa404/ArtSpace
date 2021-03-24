package com.salesianostriana.dam.artspace.retrofit

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface FollowService {

    @POST("/follow/{id}")
    fun followUser(@Header("Authorization") token : String, @Path("id") id : UUID) : Call<Void>

    @DELETE("/follow/{id}")
    fun unFollowUser(@Header("Authorization") token : String, @Path("id") id : UUID) : Call<Void>


}