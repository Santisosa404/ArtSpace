package com.salesianostriana.dam.artspace

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.salesianostriana.dam.artspace.ui.cart.CartActivity
import com.salesianostriana.dam.artspace.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_following, R.id.navigation_trending, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val sharedPref =
            getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        token = sharedPref.getString("TOKEN", "")!!

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_options_menu, menu)

        val actionLogout = menu.findItem(R.id.action_logout)

        if (token.isEmpty()) {
            actionLogout.icon = getDrawable(R.drawable.ic_profile_user)
        }


        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                logout()
                true
            }
            R.id.action_cart ->{
                goToCart()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToCart(){
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }
    private fun logout() {
        if (token.isEmpty()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            val sharedPref =
                getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                remove("TOKEN")
                commit()
            }
            finish()
        }
    }
}
