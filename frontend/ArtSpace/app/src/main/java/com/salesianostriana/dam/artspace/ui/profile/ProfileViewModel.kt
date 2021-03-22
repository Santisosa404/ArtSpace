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

    private var _userArtWorks = MutableLiveData<MutableList<ArtWorkDTO>>()

    private lateinit var _user : ProfileResponse

    val userArtWorks : LiveData<MutableList<ArtWorkDTO>>
    get() = _userArtWorks

    val user : ProfileResponse
    get() = _user

    init {
        _userArtWorks.value = mutableListOf()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ProfileService::class.java)
        getMyProfile()
    }
    private  fun getMyProfile(){
        service.getMyProfile().enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if(response.code() == 200){
                    _userArtWorks.value = response.body()!!.artWorks!!
                    _user = response.body()!!
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.i(":::TAG", "He entrado on failure")
            }
        })
    }

}