package com.salesianostriana.dam.artspace.ui.artwork

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.CommentBodyDTO
import com.salesianostriana.dam.artspace.poko.CommentDTO
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import com.salesianostriana.dam.artspace.retrofit.ArtWorkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class DetailsViewModel : ViewModel() {


    val baseUrl = "http://10.0.2.2:4141"
    var retrofit: Retrofit
    var service: ArtWorkService
    private val _artwork = MutableLiveData<ArtWorkDTO>()

    private var _comments = MutableLiveData<List<CommentDTO>>()

    val artwork: LiveData<ArtWorkDTO>
        get() = _artwork

    val comments: LiveData<List<CommentDTO>>
        get() = _comments

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArtWorkService::class.java)
    }

    fun getDetails(token: String, id: UUID) {
        service.getOne("Bearer $token", id).enqueue(object : Callback<ArtWorkDTO> {
            override fun onResponse(call: Call<ArtWorkDTO>, response: Response<ArtWorkDTO>) {
                if (response.code() == 200) {
                    _artwork.value = response.body()
                    _comments.value = artwork.value!!.comments
                }
            }

            override fun onFailure(call: Call<ArtWorkDTO>, t: Throwable) {
                Log.i(":::TAG", "On failure details")
            }
        })
    }

    fun postComment(token: String, id: UUID, commentBodyDTO: CommentBodyDTO) {
        service.addComment("Bearer $token", id, commentBodyDTO).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.code() == 204){
                    Log.i(":::TAG","Publicado")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
            null
            }
        })
    }
}

