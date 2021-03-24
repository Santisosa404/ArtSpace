package com.salesianostriana.dam.artspace.retrofit

import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*

interface LikeService {

    @POST("/like/{id}")
    fun likeArtWork(@Header("Authorization") token : String, id : UUID)

    @DELETE("/like/{id}")
    fun dislikeArtWork(@Header("Authorization") token : String, id : UUID)

}