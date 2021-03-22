package com.salesianostriana.dam.artspace.ui.profile

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ProfileResponse


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyProfileRecyclerViewAdapter(
    private val activity: Context,
    private var values: MutableList<ArtWorkDTO>,
    private var user : ProfileResponse
) : RecyclerView.Adapter<MyProfileRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val id = item.id
        holder.artDescription.text = item.description
        holder.artPrice.text = item.price.toString()
        holder.artTitle.text = item.tittle
        holder.profDescription.text = user.description
        holder.profNumFoll.text = user.artWorks!!.size.toString()
    }

    override fun getItemCount(): Int = values.size

    fun setData(newArtWorksList : MutableList<ArtWorkDTO>,newUser: ProfileResponse){
        if(newArtWorksList != null){
            values = newArtWorksList
            notifyDataSetChanged()
        }
        if(newUser != null){
            user = newUser
            notifyDataSetChanged()
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artTitle: TextView = view.findViewById(R.id.textView_prof_pub_tittle)
        val artPrice : TextView = view.findViewById(R.id.textView_prof_pub_price)
        val artDescription : TextView = view.findViewById(R.id.textView_prof_pub_description)
        val profDescription : TextView = view.findViewById(R.id.textView_prof_description)
        val profNumFoll : TextView = view.findViewById(R.id.textView_prof_following_count)
    }




}