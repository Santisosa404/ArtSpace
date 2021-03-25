package com.salesianostriana.dam.artspace.retrofit

import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.CommentBodyDTO
import com.salesianostriana.dam.artspace.poko.CommentDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface ArtWorkService {


    @GET("/artwork/")
    fun getAll() : Call<List<ArtWorkDTO>>

    @GET("/artwork/{id}")
    fun getOne(@Header("Authorization") token : String, @Path("id") id : UUID) : Call<ArtWorkDTO>

    @POST("/artwork/{id}")
    fun uploadImage(@Header("Authorization") token : String, @Part("artworkDTO") artWorkDTO: ArtWorkDTO, @Part("file") file : MultipartBody)

    @POST("/artwork/{id}/comment")
    fun addComment(@Header("Authorization") token : String, @Path("id") id : UUID, @Body commentBodyDTO : CommentBodyDTO) : Call<Void>


}