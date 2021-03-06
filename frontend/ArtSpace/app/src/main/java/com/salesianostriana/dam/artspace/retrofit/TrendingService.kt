package com.salesianostriana.dam.artspace.retrofit

import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TrendingService {

    @GET("/trending/")
    fun getTrending(@Header("Authorization") token : String) : Call<List<ArtWorkDTO>>


}