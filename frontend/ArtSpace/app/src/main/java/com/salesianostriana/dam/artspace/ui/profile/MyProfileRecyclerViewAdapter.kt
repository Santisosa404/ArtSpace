package com.salesianostriana.dam.artspace.ui.profile

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import coil.load
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ProfileResponse


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyProfileRecyclerViewAdapter(
    private var artWorks : List<ArtWorkDTO>
) : RecyclerView.Adapter<MyProfileRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = artWorks[position]
        val id = item.images!!.last().img
        holder.artDescription.text = item.description
        holder.artPrice.text = item.price.toString()
        holder.artTitle.text = item.tittle
        holder.imageView.load("https://imgur.com/${id}.png")
    }

    override fun getItemCount(): Int = artWorks.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artTitle: TextView = view.findViewById(R.id.textView_prof_pub_tittle)
        val artPrice : TextView = view.findViewById(R.id.textView_prof_pub_price)
        val artDescription : TextView = view.findViewById(R.id.textView_prof_pub_description)
        val imageView : ImageView = view.findViewById(R.id.imageView_prof_pub_image)
    }

    fun setData(newArtWorkList : List<ArtWorkDTO>){
        artWorks = newArtWorkList as MutableList<ArtWorkDTO>
        notifyDataSetChanged()
    }



}