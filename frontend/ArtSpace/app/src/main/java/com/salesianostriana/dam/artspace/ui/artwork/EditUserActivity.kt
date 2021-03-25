package com.salesianostriana.dam.artspace.ui.artwork

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import com.salesianostriana.dam.artspace.poko.UserEditDTO
import com.salesianostriana.dam.artspace.poko.UserRegDTO
import com.salesianostriana.dam.artspace.retrofit.ArtWorkService
import com.salesianostriana.dam.artspace.retrofit.ProfileService
import com.salesianostriana.dam.artspace.ui.profile.ProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class EditUserActivity : AppCompatActivity() {
    val baseUrl = "http://10.0.2.2:4141"
    lateinit var retrofit: Retrofit
    lateinit var service: ProfileService
    lateinit var ctx: Context
    lateinit var token: String
    lateinit var botonSubmit : Button
    lateinit var user : ProfileResponse
    lateinit var userName : TextView
    lateinit var description : TextView
    lateinit var fullname : TextView
    lateinit var address : TextView
    lateinit var email : TextView
    lateinit var location : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        ctx = this
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ProfileService::class.java)

        onFind()
        val sharedPref =ctx.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        token = sharedPref?.getString("TOKEN", "")!!

        setUser(token)



        init(token)
    }





    fun init(token: String) {


        botonSubmit.setOnClickListener {
            service.editMyProfile("Bearer $token", UserEditDTO(userName.text.toString(),fullname.text.toString(),email.text.toString(),address.text.toString(),location.text.toString(),description.text.toString())).enqueue(object : Callback<Void> {

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.code() == 204) {
                        val intent = Intent(ctx, ProfileFragment::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.i(":::TAG","Onfailure editProfile")
                }
            })

            }

    }

    fun onFind(){
        botonSubmit = findViewById(R.id.button_edit_submit)
        userName = findViewById(R.id.editText_edit_username)
        description = findViewById(R.id.editText_edit_description)
        fullname = findViewById(R.id.editText_edit_fullname)
        address = findViewById(R.id.editText_edit_address)
        email = findViewById(R.id.editText_edit_email)
        location = findViewById(R.id.editText_edit_localidad)

    }
    fun setUser(token: String){
        service.getMyProfile("Bearer $token").enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if(response.code()== 200){
                    Log.i(":::TAG","Entro en el if 200")
                    user = response.body()!!
                    userName.text = user.username
                    description.text = user.description
                    fullname.text = user.fullname
                    description.text = user.description
                    fullname.text = user.fullname
                    address.text = user.address
                    location.text = user.location
                    email.text = user.email
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.i(":::TAG","On Failure profile")
            }
        })
    }




}