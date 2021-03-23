package com.salesianostriana.dam.artspace.ui.following

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyfollowingRecyclerViewAdapter(
    private var values: List<ArtWorkDTO>
) : RecyclerView.Adapter<MyfollowingRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_following, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.usernameView.text = item.username
        holder.descriptionView.text = item.description
        holder.priceView.text = item.price.toString()
        holder.tittleView.text = item.tittle

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameView: TextView = view.findViewById(R.id.textView_foll_username)
        val descriptionView : TextView = view.findViewById(R.id.textView_foll_description)
        val priceView : TextView = view.findViewById(R.id.textView_foll_price)
        val tittleView : TextView = view.findViewById(R.id.textView_foll_tittle)
        val imageView : ImageView = view.findViewById(R.id.imageView_foll_image)
    }

    fun setData(newFollowing: List<ArtWorkDTO>){
        values = newFollowing
        notifyDataSetChanged()
    }
}