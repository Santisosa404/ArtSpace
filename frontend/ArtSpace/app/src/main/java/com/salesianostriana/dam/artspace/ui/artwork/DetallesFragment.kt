package com.salesianostriana.dam.artspace.ui.artwork

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
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
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.CommentBodyDTO
import com.salesianostriana.dam.artspace.poko.CommentDTO
import java.util.*


class DetallesFragment : Fragment() {

    lateinit var artWorkDTO: ArtWorkDTO
    var commentList : List<CommentDTO> = listOf()
    lateinit var adapterDetalles : MydetallesRecyclerViewAdapter
    lateinit var viewModel : DetailsViewModel
    lateinit  var id : UUID
    lateinit  var id_image : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalles_list, container, false)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        id = UUID.fromString(activity?.intent!!.extras!!.getString("artWork_id"))
        id_image = activity?.intent!!.extras!!.getString("image_id")!!
        Log.i(":::TAG","la id $id")
        val sharedPref =activity?.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        val token = sharedPref?.getString("TOKEN", "")!!

        val v = view.findViewById<RecyclerView>(R.id.list_details_comment)
        val artworkTitle : TextView = view.findViewById(R.id.textView_details_tittle)
        val artworkPrice : TextView = view.findViewById(R.id.textView_details_precio)
        val artworkDescription : TextView = view.findViewById(R.id.textView_details_descripcion)
        val artworkImage : ImageView = view.findViewById(R.id.imageView_details_image)
        val artworkBoton : Button = view.findViewById(R.id.button_det_enviarComment)
        val artworkPlainText : TextView = view.findViewById(R.id.editText_det_comment)

        adapterDetalles = MydetallesRecyclerViewAdapter(commentList)

        v.layoutManager = LinearLayoutManager(context)
        v.adapter  = adapterDetalles

        viewModel.getDetails(token,id)


        viewModel.comments.observe(viewLifecycleOwner, Observer {
            commentList = it
            adapterDetalles.setData(it)
        })
        viewModel.artwork.observe(viewLifecycleOwner, Observer {
            artWorkDTO = it
            artworkTitle.text = artWorkDTO.tittle
            artworkDescription.text = artWorkDTO.description
            artworkPrice.text = artWorkDTO.price.toString()
            artworkImage.load("https://imgur.com/${id_image}.png")
            artworkBoton.setOnClickListener {
                viewModel.postComment(token,id, CommentBodyDTO(artworkPlainText.text.toString()))
            Log.i(":::TAG","El comentario ${artworkPlainText.text.toString()} ")
            }
        })
        return view
    }

}