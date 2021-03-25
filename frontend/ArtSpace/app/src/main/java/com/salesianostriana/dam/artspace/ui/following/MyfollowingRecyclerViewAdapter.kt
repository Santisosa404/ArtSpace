package com.salesianostriana.dam.artspace.ui.following

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import coil.load
import com.salesianostriana.dam.artspace.MainActivity
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ArtWorkListDTO
import com.salesianostriana.dam.artspace.ui.artwork.DetailsActivity


class MyfollowingRecyclerViewAdapter(
    private var context : Context,
    private var values: List<ArtWorkListDTO>,
    private var viewModel: FollowingListViewModel
) : RecyclerView.Adapter<MyfollowingRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_following, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val id = item.images!!.last().img
        holder.usernameView.text = item.userName
        holder.descriptionView.text = item.description
        holder.priceView.text = item.price.toString()
        holder.tittleView.text = item.tittle
        holder.imageView.load("https://imgur.com/${id}.png")
        holder.imageView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra("artWork_id",item.id.toString())
                putExtra("image_id",id)
            }
            context.startActivity(intent)
        }
        if(item.meGustaUsuario){
            holder.likeView.setImageResource(R.drawable.ic_heart_full)
        }else{
            holder.likeView.setImageResource(R.drawable.ic_heart_empty)
        }
        holder.likeView.setOnClickListener {
           //Todo preguntar
            if (item.meGustaUsuario) {
                viewModel.disLike(getToken(), item.id!!)
                holder.likeView.setImageResource(R.drawable.ic_heart_empty)
                item.meGustaUsuario = false
            } else {
                viewModel.like(getToken(), item.id!!)
                holder.likeView.setImageResource(R.drawable.ic_heart_full)
                item.meGustaUsuario = true
            }
        }
        holder.unFollowView.setOnClickListener{
            viewModel.unFollow(getToken(),item.userId!!)
        }

    }

    fun getToken() : String {
        val sharedPref =context.getSharedPreferences(context.getString(R.string.preference_file_name), Context.MODE_PRIVATE)
       return sharedPref?.getString("TOKEN", "")!!
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usernameView: TextView = view.findViewById(R.id.textView_foll_username)
        val descriptionView : TextView = view.findViewById(R.id.textView_foll_description)
        val priceView : TextView = view.findViewById(R.id.textView_foll_price)
        val tittleView : TextView = view.findViewById(R.id.textView_foll_tittle)
        val imageView : ImageView = view.findViewById(R.id.imageView_foll_image)
        val likeView : ImageView = view.findViewById(R.id.imageView_foll_likeEmpty)
        val unFollowView : TextView = view.findViewById(R.id.textView_foll_unfollow)
    }

    fun setData(newFollowing: List<ArtWorkListDTO>){
        values = newFollowing
        notifyDataSetChanged()
    }
}