package com.salesianostriana.dam.artspace.ui.register

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.retrofit.AuthService
import retrofit2.Retrofit

class RegisterActivity : AppCompatActivity() {

    lateinit var etUsername : EditText
    lateinit var etEmail : EditText
    lateinit var etFullName : EditText
    lateinit var etPass : EditText
    lateinit var etLocation : EditText
    lateinit var etAddress : EditText
    lateinit var btRegistro : Button

    val baseUrl = "http://10.0.2.2:4141"
    lateinit var retrofit: Retrofit
    lateinit var service: AuthService
    lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
        event()
    }
    fun init(){
        etUsername = findViewById(R.id.editText_register_username)
        etEmail = findViewById(R.id.editText_register_email)
        etFullName= findViewById(R.id.editText_register_fullname)
        etPass = findViewById(R.id.editText_register_password)
        etLocation = findViewById(R.id.editText_register_location)
        etAddress = findViewById(R.id.editText_register_address)
        btRegistro = findViewById(R.id.button_register)
    }

    fun event(){
        btRegistro.setOnClickListener{
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val fullname = etFullName.text.toString()
            val password = etPass.text.toString()
            val location = etLocation.text.toString()
            val address = etAddress.text.toString()
            if(username.isNotBlank() && email.isNotBlank() && fullname.isNotBlank() && password.isNotBlank() && location.isNotBlank() && address.isNotBlank()){

            }else{
                Log.i(":::TAG","Alguno de los campos no es correcto")
            }
        }
    }
}