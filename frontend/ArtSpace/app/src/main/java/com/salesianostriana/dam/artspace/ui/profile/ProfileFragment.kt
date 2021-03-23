package com.salesianostriana.dam.artspace.ui.profile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import com.salesianostriana.dam.artspace.ui.trending.MyTrendingRecyclerViewAdapter

/**
 * A fragment representing a list of Items.
 */
class ProfileFragment : Fragment() {

    lateinit var artWorksList : MutableList<ArtWorkDTO>
    lateinit var adapterProfile : MyProfileRecyclerViewAdapter
    lateinit var viewModel : ProfileViewModel
    lateinit var user : ProfileResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_list, container, false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        artWorksList= mutableListOf()
        user = ProfileResponse()
        adapterProfile = MyProfileRecyclerViewAdapter(activity as Context, artWorksList,user)

        val v = view as RecyclerView

        v.layoutManager = LinearLayoutManager(context)
        v.adapter = adapterProfile

        viewModel.userArtWorks.observe(viewLifecycleOwner, Observer {
            artWorksList = it
            adapterProfile.setData(artWorksList,user)
        })



        return view
    }


}