package com.example.climberapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.climberapp.R
import com.example.climberapp.manager.SpinnerApiManager
import com.example.climberapp.manager.TripsApiManager
import com.example.climberapp.ui.adapter.RecyclerAdapterDescubre

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    //RecyclerView
    private lateinit var mRecyclerView : RecyclerView
    private  var mAdapter = RecyclerAdapterDescubre()
    private var tripsApiManager = TripsApiManager()
    private var spinnerApiManager = SpinnerApiManager()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        setUpRecyclerView(root, "Todas")

        val btProximoFinde = root.findViewById(R.id.btProximoFinde) as Button
        val btTodas = root.findViewById(R.id.btTodas) as Button
        val btBoulder = root.findViewById(R.id.btBoulder) as Button
        val btClasica = root.findViewById(R.id.btClasica) as Button
        val btDeportiva = root.findViewById(R.id.btDeportiva) as Button
        val btRocodromo = root.findViewById(R.id.btRocodromo) as Button

        var button: MutableList<Button> = mutableListOf(btProximoFinde, btTodas, btBoulder,  btClasica,btDeportiva, btRocodromo)

        btTodas.setOnClickListener {
            changeColor(btTodas, button)
            setUpRecyclerView(root,"Todas")
        }

        btProximoFinde.setOnClickListener {
            changeColor(btProximoFinde, button)
            setUpRecyclerView(root,"ProximaSemana")
        }

        btBoulder.setOnClickListener {
            changeColor(btBoulder, button)
            setUpRecyclerView(root,"Boulder")
        }
        btClasica.setOnClickListener {
            changeColor(btClasica, button)
            setUpRecyclerView(root,"Clasica")
        }
        btDeportiva.setOnClickListener {
            changeColor(btDeportiva, button)
            setUpRecyclerView(root,"Deportiva")
        }
        btRocodromo.setOnClickListener {
            changeColor(btRocodromo, button)
            setUpRecyclerView(root,"Rocodromo")
        }
        val spinner: Spinner = root.findViewById(R.id.locationSpinner)

        val lista = listOf("1","2","3","4")

        val adaptador = ArrayAdapter(requireContext(), R.layout.style_spinner1, spinnerApiManager.getComunidades())
        spinner.adapter = adaptador

        return root
    }
    fun changeColor(buttonFocused: Button, buttonNotFocused: List<Button>){
        for (b in buttonNotFocused){
            b.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
            b.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorGreyNormal))
        }
        buttonFocused.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.colorBlack ))
        buttonFocused.setTextColor( ContextCompat.getColor(requireContext(), R.color.colorWhite))
    }

    private fun setUpRecyclerView(root: View, action: String){
        if(action == "Todas")
            mAdapter.RecyclerAdapterDescubre(tripsApiManager.get(mAdapter))
        else
            mAdapter.RecyclerAdapterDescubre(
                tripsApiManager.getByParameters(mAdapter, action))
        mRecyclerView = root.findViewById(R.id.rvDescrubre)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter
    }
}