package com.salesianostriana.dam.artspace.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.salesianostriana.dam.artspace.R
import com.salesianostriana.dam.artspace.poko.ArtWorkDTO
import com.salesianostriana.dam.artspace.poko.ProfileResponse
import com.salesianostriana.dam.artspace.poko.UserDTO
import com.salesianostriana.dam.artspace.ui.artwork.EditUserActivity
import com.salesianostriana.dam.artspace.ui.login.LoginActivity
import com.salesianostriana.dam.artspace.ui.trending.MyTrendingRecyclerViewAdapter

/**
 * A fragment representing a list of Items.
 */
class ProfileFragment : Fragment() {

    var artWorksList : List<ArtWorkDTO> = listOf()
    lateinit var user : ProfileResponse
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
        val token = sharedPref?.getString("TOKEN", "")!!

        val v = view.findViewById<RecyclerView>(R.id.list)
        val userName = view.findViewById<TextView>(R.id.textView_prof_username)
        val profDescription : TextView = view.findViewById(R.id.textView_prof_description)
        val profNumFoll : TextView = view.findViewById(R.id.textView_prof_following_count)
        val profEdit : ImageView = view.findViewById(R.id.image_edit)

        adapterProfile = MyProfileRecyclerViewAdapter(artWorksList)

        v.layoutManager = LinearLayoutManager(context)
        v.adapter = adapterProfile

        viewModel.getMyProfile(token)

        viewModel.artWorks.observe(viewLifecycleOwner, Observer {
            artWorksList = it
            adapterProfile.setData(it)
        })
        viewModel.user.observe(viewLifecycleOwner, Observer {
            user = it
            userName.text = user.username
            profDescription.text = user.description
            profNumFoll.text = user.following!!.size.toString()
            profEdit.setOnClickListener {
                val intent = Intent(context, EditUserActivity::class.java).apply {

                    putExtra("user_id",user.id)
                }
                startActivity(intent)
            }

        })

        return view
    }


}