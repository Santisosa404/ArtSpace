package com.salesianostriana.dam.artspace.retrofit
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileService {
    @GET("/profile/")
    fun getMyProfile(@Header("Authorization") token : String) : Call<ProfileResponse>
}