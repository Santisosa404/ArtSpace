package com.salesianostriana.dam.artspace.ui.trending

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

/**
 * A fragment representing a list of Items.
 */
class TrendingFragment : Fragment() {

    var listTrendig : List<ArtWorkDTO> = listOf()
    lateinit var  listAdapter : MyTrendingRecyclerViewAdapter
    lateinit var viewModel : TrendigListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trending_list, container, false)
        viewModel = ViewModelProvider(this).get(TrendigListViewModel :: class.java)

        val sharedPref =activity?.getSharedPreferences(getString(R.string.preference_file_name), Context.MODE_PRIVATE)
        var token = sharedPref?.getString("TOKEN", "")!!

        val v = view as RecyclerView

        listAdapter = MyTrendingRecyclerViewAdapter(listTrendig)
        v.layoutManager = LinearLayoutManager(context)
        v.adapter = listAdapter

        viewModel.getTrending(token)
        viewModel.trendig.observe(viewLifecycleOwner, Observer {
            listTrendig = it
            listAdapter.setData(it)
        })

        return view
    }
}