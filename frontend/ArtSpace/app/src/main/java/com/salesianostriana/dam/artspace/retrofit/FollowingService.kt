package com.salesianostriana.dam.artspace.retrofit

import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ArtWorkListDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface FollowingService {

    @GET("/following/")
    fun getFollowing(@Header("Authorization") token : String) : Call<List<ArtWorkListDTO>>

}