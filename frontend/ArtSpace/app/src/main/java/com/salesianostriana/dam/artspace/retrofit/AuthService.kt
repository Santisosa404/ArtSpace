package com.salesianostriana.dam.artspace.retrofit

import com.salesianostriana.dam.artspace.poko.LoginRequest
import com.salesianostriana.dam.artspace.poko.LoginResponse
import com.salesianostriana.dam.artspace.poko.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>


    @POST("auth/register")
    fun register(@Body usuario:RegisterRequest) : Call<Void>
}