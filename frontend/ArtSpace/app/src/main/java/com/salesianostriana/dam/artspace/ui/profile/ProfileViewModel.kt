package com.salesianostriana.dam.artspace.ui.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import com.salesianostriana.dam.artspace.poko.UserDTO
import com.salesianostriana.dam.artspace.retrofit.AuthService
import com.salesianostriana.dam.artspace.retrofit.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileViewModel : ViewModel() {
    val baseUrl = "http://10.0.2.2:4141"
    lateinit var retrofit: Retrofit
    lateinit var service: ProfileService


    private lateinit var _user: ProfileResponse


    val user: ProfileResponse
        get() = _user

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ProfileService::class.java)
    }

    private fun getMyProfile(token: String) {
        if (token.isNotEmpty()) {
            service.getMyProfile("Bearer $token").enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>
                ) {
                    if (response.code() == 200) {
                        _user = response.body()!!
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.i(":::TAG", "He entrado on failure profile")
                }
            })
        }
    }

}