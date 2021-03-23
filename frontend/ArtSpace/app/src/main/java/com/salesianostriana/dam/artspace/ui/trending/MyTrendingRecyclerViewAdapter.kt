package com.salesianostriana.dam.artspace.ui.trending

import android.media.Image
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyTrendingRecyclerViewAdapter(
    private var values: List<ArtWorkDTO>
) : RecyclerView.Adapter<MyTrendingRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_trending, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val id = item.images!!.last().img
        holder.usernameView.text = item.username
        holder.descriptionView.text = item.description
        holder.priceView.text = item.price.toString()
        holder.tittleView.text = item.tittle
        holder.imageView.load("https://imgur.com/${id}.png")

//        holder.imageView.load()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameView: TextView = view.findViewById(R.id.textView_trend_username)
        val descriptionView : TextView = view.findViewById(R.id.textView_trend_description)
        val priceView : TextView = view.findViewById(R.id.textView_trend_price)
        val tittleView : TextView = view.findViewById(R.id.textView_trend_tittle)
        val imageView : ImageView = view.findViewById(R.id.imageView_trend_image)
    }

    fun setData(newTrending: List<ArtWorkDTO>){
        values = newTrending
        notifyDataSetChanged()
    }
}