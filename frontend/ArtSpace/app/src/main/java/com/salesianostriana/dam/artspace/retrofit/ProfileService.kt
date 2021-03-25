package com.salesianostriana.dam.artspace.retrofit
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import com.salesianostriana.dam.artspace.poko.UserEditDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface ProfileService {
    @GET("/profile/")
    fun getMyProfile(@Header("Authorization") token : String) : Call<ProfileResponse>

    @PUT("/profile/")
    fun editMyProfile(@Header("Authorization") token : String, @Body editDTO: UserEditDTO) : Call<Void>
}