package com.salesianostriana.dam.artspace.ui.following

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
import com.salesianostriana.dam.artspace.poko.ArtWorkListDTO

/**
 * A fragment representing a list of Items.
 */
class FollowingFragment : Fragment() {
    var listFollowing : List<ArtWorkListDTO> = listOf()
    lateinit var listAdapter : MyfollowingRecyclerViewAdapter
    lateinit var viewModel: FollowingListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_following_list,container,false)
        viewModel= ViewModelProvider(this).get(FollowingListViewModel :: class.java)

        val sharedPref =activity?.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        var token = sharedPref?.getString("TOKEN", "")!!

        val v = view as RecyclerView

        listAdapter = MyfollowingRecyclerViewAdapter(activity as Context,listFollowing,viewModel)
        v.layoutManager = LinearLayoutManager(context)
        v.adapter = listAdapter
        viewModel.getFollowing(token)


        viewModel.following.observe(viewLifecycleOwner, Observer {
            listFollowing = it
            listAdapter.setData(it)
        })

        return view
    }
}