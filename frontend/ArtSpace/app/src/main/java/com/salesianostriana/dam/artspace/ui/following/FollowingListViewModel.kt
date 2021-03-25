package com.salesianostriana.dam.artspace.ui.following

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ArtWorkListDTO
import com.salesianostriana.dam.artspace.retrofit.FollowService
import com.salesianostriana.dam.artspace.retrofit.FollowingService
import com.salesianostriana.dam.artspace.retrofit.LikeService
import com.salesianostriana.dam.artspace.retrofit.TrendingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class FollowingListViewModel : ViewModel() {
    private val _following = MutableLiveData<List<ArtWorkListDTO>>()
    private val baseUrl = "http://10.0.2.2:4141"
    private var service: FollowingService
    private var likeService : LikeService
    private var followService : FollowService
    val following: LiveData<List<ArtWorkListDTO>>
        get() = _following

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(FollowingService::class.java)
        likeService = retrofit.create(LikeService::class.java)
        followService = retrofit.create(FollowService ::class.java)
    }

    fun getFollowing(token: String) {
        if (token.isEmpty()) {
            Log.i(":::TAG", "Token vacio")
        } else {
            service.getFollowing("Bearer $token").enqueue(object : Callback<List<ArtWorkListDTO>> {
                override fun onResponse(
                    call: Call<List<ArtWorkListDTO>>,
                    response: Response<List<ArtWorkListDTO>>
                ) {
                    if (response.code() == 200)
                        _following.value = response.body()
                }

                override fun onFailure(call: Call<List<ArtWorkListDTO>>, t: Throwable) {
                    Log.i(":::TAG", "Failure en following")
                }
            })
        }
    }

    fun like(token: String, id: UUID) {
        if (token.isEmpty()){
            Log.i(":::TAG", "Token vacio")
        }else{
            likeService.likeArtWork("Bearer $token",id).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    //Cambiar el icono?¿
                    if(response.code()==200){
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i(":::TAG","On failure like")
                }
            })
        }
    }
    fun disLike(token: String, id: UUID) {
        if (token.isEmpty()){
            Log.i(":::TAG", "Token vacio")
        }else{
            likeService.dislikeArtWork("Bearer $token",id).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if(response.code()==200){
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i(":::TAG","On failure like")
                }
            })
        }
    }
    fun unFollow(token: String, id: UUID){
        if(token.isEmpty()){
            Log.i(":::TAG","Token vacio")
        }else{
            followService.unFollowUser("Bearer $token",id).enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.code() == 200){

                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i(":::TAG","On failura unfollow")
                }
            })
        }
    }
}