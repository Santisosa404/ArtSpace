package com.salesianostriana.dam.artspace.ui.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.dam.artspace.poko.ArtWorkCartDTO
import com.salesianostriana.dam.artspace.poko.CartDTO
import com.salesianostriana.dam.artspace.retrofit.CartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class CarritoListViewModel :ViewModel(){

    val baseUrl = "http://10.0.2.2:4141"
    var retrofit : Retrofit
    private  val _artworkCart = MutableLiveData<List<ArtWorkCartDTO>>()
    private val _cartDTO = MutableLiveData<CartDTO>()
    private val service : CartService

    val artworkCart : LiveData<List<ArtWorkCartDTO>>
    get() = _artworkCart

    val cart : LiveData<CartDTO>
    get() = _cartDTO

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(CartService::class.java)
    }

    fun getCart(token : String){
        service.getCart("Bearer $token").enqueue(object :Callback<CartDTO>{
            override fun onResponse(call: Call<CartDTO>, response: Response<CartDTO>) {
                if (response.code()==200){
                    _cartDTO.value = response.body()
                    _artworkCart.value = cart.value!!.orders
                }
            }

            override fun onFailure(call: Call<CartDTO>, t: Throwable) {
            }
        })
    }

    fun buyCart(token: String){
        service.buyCart("Bearer $token").enqueue(object :Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }
}