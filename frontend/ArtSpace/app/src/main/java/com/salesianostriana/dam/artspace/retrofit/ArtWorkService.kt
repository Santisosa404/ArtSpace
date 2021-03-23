package com.salesianostriana.dam.artspace.retrofit

import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import retrofit2.Call
import retrofit2.http.GET

interface ArtWorkService {


    @GET("/artwork/")
    fun getAll() : Call<List<ArtWorkDTO>>

}