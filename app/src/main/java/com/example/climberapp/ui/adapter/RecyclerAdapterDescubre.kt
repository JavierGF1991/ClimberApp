package com.example.climberapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.climberapp.R
import com.example.climberapp.ui.classLayer.Trip
import com.squareup.picasso.Picasso

class RecyclerAdapterDescubre : RecyclerView.Adapter<RecyclerAdapterDescubre.ViewHolder>() {

    private lateinit var trip: MutableList<Trip>

    fun RecyclerAdapterDescubre(trip : MutableList<Trip>){
        this.trip = trip
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trip[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_rvdescubre, parent, false))
    }

    override fun getItemCount(): Int {
        return trip.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tripType = view.findViewById(R.id.tvType) as TextView
        private val tripZone = view.findViewById(R.id.tvZone) as TextView
        private val tripDate = view.findViewById(R.id.tvDate) as TextView
        private val tripPeople = view.findViewById(R.id.tvPeople) as TextView
        private val tripDegrees = view.findViewById(R.id.tvDegrees) as TextView
        private val tripPhoto = view.findViewById(R.id.ivPhoto) as ImageView

        fun bind(trip:Trip){
            tripType.text = trip.type
            tripZone.text = trip.zone
            tripDate.text = trip.date
            tripPeople.text = trip.people
            tripDegrees.text = trip.degrees

            tripPhoto.setOnClickListener{

            }

        }
    }
}