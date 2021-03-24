package com.salesianostriana.dam.artspace.retrofit

import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*

interface FollowService {

    @POST("/follow/{id}")
    fun followUser(@Header("Authorization") token : String, id : UUID)

    @DELETE("/follow/{id}")
    fun unFollowUser(@Header("Authorization") token : String, id : UUID)


}