package com.salesianostriana.dam.artspace.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.salesianostriana.dam.artspace.MainActivity
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.LoginRequest
import com.salesianostriana.dam.artspace.poko.LoginResponse
import com.salesianostriana.dam.artspace.retrofit.AuthService
import com.salesianostriana.dam.artspace.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    lateinit var etUsername: EditText
    lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var btnRegistro: Button

    val baseUrl = "http://10.0.2.2:4141"
    lateinit var retrofit: Retrofit
    lateinit var service: AuthService
    lateinit var ctx: Context
    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        actionBar?.hide()
        init()
        events()
    }
    private fun events(){
        btnLogin.setOnClickListener(View.OnClickListener {
            val username = etUsername.text.toString()
            val password = etPass.text.toString()

            if (username.isNotBlank() && password.isNotBlank()) {
                Log.i(":::TAG","Entro en el if")
                service.login(LoginRequest(username, password)).enqueue(object:Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        Log.i(":::TAG",response.code().toString())
                        if (response.code()==200){
                            val sharedPref = ctx.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
                            token = response.body()?.token!!
                            Log.i("TOKEN", "${token}, ${etUsername}")

                            with(sharedPref.edit()) {
                                putString("TOKEN", response.body()?.token)
                                commit()
                            }

                            etUsername.text.clear()
                            etPass.text.clear()

                            val intent = Intent(ctx, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                        Log.i(":::APP", "Error en petición de Login")
                    }

                })
            } else {
                etUsername.error = "Usuario incorrecto"
                etPass.error = "Password incorrecto"
            }
        })
        btnRegistro.setOnClickListener {
            Log.i(":::TAG","Entro")
            val intent = Intent(ctx, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        etUsername = findViewById(R.id.editText_login_username)
        etPass = findViewById(R.id.editText_login_password)
        btnLogin = findViewById(R.id.button_login)
        btnRegistro = findViewById(R.id.button_register)

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(AuthService::class.java)

        ctx = this
    }
}