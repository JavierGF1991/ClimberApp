package com.example.climberapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.climberapp.R
import com.example.climberapp.ui.adapter.RecyclerAdapterDescubre
import com.example.climberapp.ui.classLayer.User
import com.example.climberapp.ui.models.Trips
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var mRecyclerView : RecyclerView
    private val mAdapter : RecyclerAdapterDescubre = RecyclerAdapterDescubre()
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        mRecyclerView = root.findViewById(R.id.rvDescrubre)
        setUpRecyclerView()

        return root
    }

    private fun setUpRecyclerView(){

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter.RecyclerAdapterDescubre(getDataTrips())
        mRecyclerView.adapter = mAdapter
    }

    private fun getDataTrips() : MutableList<Trips>{

        var trips: MutableList<Trips> = ArrayList()
        var users: MutableList<User> = ArrayList()
        users.add(User("AAA","AAA","AAA","AAA"))
        users.add(User("BBB","BBB","BBB","BBB"))
        users.add(User("CCC","CCC","CCC","CCC"))
        users.add(User("DDD","DDD","DDD","DDD"))
        trips.add(Trips("Boulder","Albarracín", "19 Sept", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",2, users))
        trips.add(Trips("Clasica","Pico de la miel", "12 Sept", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",5, users))
        trips.add(Trips("Desportiva","Rodellar", "21 Oct", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",3, users))
        trips.add(Trips("Boulder","Tamajon", "19 Ene", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",1, users))

        return trips
    }
}