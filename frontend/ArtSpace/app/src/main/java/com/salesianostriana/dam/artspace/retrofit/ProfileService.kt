package com.salesianostriana.dam.artspace.retrofit
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProfileService {
    @GET("/profile/")
    fun getMyProfile() : Call<ProfileResponse>
}