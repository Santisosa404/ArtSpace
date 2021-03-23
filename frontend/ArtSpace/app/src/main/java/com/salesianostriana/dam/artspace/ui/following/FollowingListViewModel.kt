package com.salesianostriana.dam.artspace.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.retrofit.FollowingService
import com.salesianostriana.dam.artspace.retrofit.TrendingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FollowingListViewModel : ViewModel() {
    private val _following = MutableLiveData<List<ArtWorkDTO>>()
    private val baseUrl = "http://10.0.2.2:4141"
    private lateinit var service: FollowingService

    val following: LiveData<List<ArtWorkDTO>>
        get() = _following

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(FollowingService::class.java)
    }

    fun getFollowing(token: String){
        if (token.isEmpty())
            Log.i(":::TAG","Token vacio")
        service.getFollowing("Bearer $token").enqueue(object : Callback<List<ArtWorkDTO>>{
            override fun onResponse(
                call: Call<List<ArtWorkDTO>>,
                response: Response<List<ArtWorkDTO>>
            ) {
                if(response.code() == 200)
                    _following.value = response.body()
            }

            override fun onFailure(call: Call<List<ArtWorkDTO>>, t: Throwable) {
                Log.i(":::TAG", "Failure en following")
            }
        })

    }

}