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
import com.salesianostriana.dam.artspace.poko.UserDTO
import com.salesianostriana.dam.artspace.ui.trending.MyTrendingRecyclerViewAdapter

/**
 * A fragment representing a list of Items.
 */
class ProfileFragment : Fragment() {

    var artWorksList : List<ArtWorkDTO> = listOf()
    var user : ProfileResponse = ProfileResponse()
    lateinit var adapterProfile : MyProfileRecyclerViewAdapter
    lateinit var viewModel : ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_list, container, false)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val sharedPref =activity?.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        var token = sharedPref?.getString("TOKEN", "")!!

        //No lo puede castear TODO
        val v = view as RecyclerView

        adapterProfile = MyProfileRecyclerViewAdapter(user)

        v.layoutManager = LinearLayoutManager(context)
        v.adapter = adapterProfile




        return view
    }


}