package com.salesianostriana.dam.artspace.ui.trending

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.retrofit.TrendingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.salesianostriana.dam.artspace.retrofit.ArtWorkService
import com.salesianostriana.dam.artspace.retrofit.FollowService
import com.salesianostriana.dam.artspace.ui.login.LoginActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TrendigListViewModel : ViewModel() {
    private val _trending = MutableLiveData<List<ArtWorkDTO>>()
    private val baseUrl = "http://10.0.2.2:4141"
    private var service: TrendingService
    private var artWorkService: ArtWorkService
    private var followService : FollowService

    val trendig: LiveData<List<ArtWorkDTO>>
        get() = _trending

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(TrendingService::class.java)
        artWorkService = retrofit.create(ArtWorkService::class.java)
        followService = retrofit.create(FollowService ::class.java)
    }

    fun getTrending(token: String) {
        if (token.isEmpty()) {
            artWorkService.getAll().enqueue(object : Callback<List<ArtWorkDTO>> {
                override fun onResponse(
                    call: Call<List<ArtWorkDTO>>,
                    response: Response<List<ArtWorkDTO>>
                ) {
                    if (response.code() == 200) {
                        _trending.value = response.body()
                    }
                }

                override fun onFailure(call: Call<List<ArtWorkDTO>>, t: Throwable) {
                    Log.i(":::TAG", "Failure en get all")
                }
            })
        } else {
            service.getTrending("Bearer $token").enqueue(object : Callback<List<ArtWorkDTO>> {

                override fun onResponse(
                    call: Call<List<ArtWorkDTO>>,
                    response: Response<List<ArtWorkDTO>>
                ) {
                    if (response.code() == 200) {
                        _trending.value = response.body()
                    }
                }
                override fun onFailure(call: Call<List<ArtWorkDTO>>, t: Throwable) {
                    Log.i(":::TAG", "On failure trending")
                }
            })
        }
    }

    fun doFollow(token: String, id : UUID){
        followService.followUser(token, id)
    }

}