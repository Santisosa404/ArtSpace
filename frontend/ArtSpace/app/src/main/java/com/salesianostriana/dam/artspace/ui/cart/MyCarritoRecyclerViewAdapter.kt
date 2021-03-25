package com.salesianostriana.dam.artspace.ui.cart

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkCartDTO

class MyCarritoRecyclerViewAdapter(
    private var values: List<ArtWorkCartDTO>
) : RecyclerView.Adapter<MyCarritoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_carrito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val id = item.images!!.last().img
        holder.artworkImage.load("https://imgur.com/${id}.png")
        holder.artworkMaterial.text = item.material
        holder.artworkTitle.text = item.tittle
        holder.artworkPrice.text = item.price.toString()
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artworkTitle : TextView = view.findViewById(R.id.textView_cart_art_title)
        val artworkPrice : TextView = view.findViewById(R.id.textView_cart_art_precio)
        val artworkImage : ImageView = view.findViewById(R.id.imageView_cart_image)
        val artworkMaterial : TextView = view.findViewById(R.id.textView_cart_art_material)
    }

    fun setData(newArtWorkCartList : List<ArtWorkCartDTO>){
        values = newArtWorkCartList
        notifyDataSetChanged()
    }
}