package com.salesianostriana.dam.artspace.ui.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.salesianostriana.dam.artspace.MainActivity
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkCartDTO
import com.salesianostriana.dam.artspace.poko.CartDTO

/**
 * A fragment representing a list of Items.
 */
class CarritoFragment : Fragment() {

    var  artWorks : List<ArtWorkCartDTO> = listOf()
    lateinit var cart : CartDTO
    lateinit var adapterCarrito : MyCarritoRecyclerViewAdapter
    lateinit var viewModel: CarritoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito_list, container, false)
        viewModel = ViewModelProvider(this).get(CarritoListViewModel::class.java)
        val sharedPref =activity?.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val token = sharedPref?.getString("TOKEN", "")!!

        val v = view.findViewById<RecyclerView>(R.id.list_cart_artwork)
        val totalPrice : TextView = view.findViewById(R.id.textView_cart_total_precio)
        val submitCart : Button = view.findViewById(R.id.button_cart_pagar)

        adapterCarrito = MyCarritoRecyclerViewAdapter(artWorks)

        v.layoutManager = LinearLayoutManager(context)
        v.adapter = adapterCarrito

        viewModel.getCart(token)

        viewModel.cart.observe(viewLifecycleOwner, Observer {
            cart = it
            totalPrice.text = cart.finalPrice.toString()
            submitCart.setOnClickListener{
                viewModel.buyCart(token)
                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)
            }
        })
        viewModel.artworkCart.observe(viewLifecycleOwner, Observer {
            artWorks = it
            adapterCarrito.setData(it)
        })
        return view
    }


}