package com.salesianostriana.dam.artspace.ui.artwork

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.retrofit.ArtWorkService
import com.salesianostriana.dam.artspace.retrofit.AuthService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
    }

    val baseUrl = "http://10.0.2.2:4141"
    lateinit var retrofit: Retrofit
    lateinit var service: ArtWorkService
    lateinit var ctx: Context
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_upload)
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArtWorkService::class.java)
        ctx = this
    }

    private fun init(){

    }
}