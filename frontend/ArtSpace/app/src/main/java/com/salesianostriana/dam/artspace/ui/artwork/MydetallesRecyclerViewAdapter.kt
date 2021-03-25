package com.salesianostriana.dam.artspace.ui.artwork

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.CommentDTO



class MydetallesRecyclerViewAdapter(
    private var values: List<CommentDTO>
) : RecyclerView.Adapter<MydetallesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_detalles, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.commentBody.text = item.body
        holder.commentUsername.text = item.username

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val commentUsername: TextView = view.findViewById(R.id.textView_comment_username)
        val commentBody : TextView = view.findViewById(R.id.textView_comment_body)
    }

    fun setData(newCommentList: List<CommentDTO>) {
        values = newCommentList
        notifyDataSetChanged()
    }

}