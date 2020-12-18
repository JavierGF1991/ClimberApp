package com.example.climberapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.climberapp.R
import com.example.climberapp.ui.models.Trips
import com.squareup.picasso.Picasso

class RecyclerAdapterDescubre: RecyclerView.Adapter<RecyclerAdapterDescubre.ViewHolder>() {

    var trips: MutableList<Trips>  = ArrayList()
    /*lateinit var context: Context*/

    fun RecyclerAdapterDescubre(trips : MutableList<Trips>){
        this.trips = trips
        /*this.context = context*/
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trips.get(position)
        holder.bind(item/*, context*/)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_rvdescubre, parent, false))
    }
    override fun getItemCount(): Int {
        return trips.size
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tripType = view.findViewById(R.id.tvType) as TextView
        val tripZone = view.findViewById(R.id.tvZone) as TextView
        val tripDate = view.findViewById(R.id.tvDate) as TextView
        val tripPeople = view.findViewById(R.id.tvPeople) as TextView
        val tripDegrees = view.findViewById(R.id.tvDegrees) as TextView
        val tripPhoto = view.findViewById(R.id.ivPhoto) as ImageView

        fun bind(trip:Trips/*, context: Context*/){
            tripType.text = trip.type
            tripZone.text = trip.zone
            tripDate.text = trip.date
            for (i in trip.people){
                tripPeople.text = tripPeople.text.toString() + ", " + i.email
            }
            tripDegrees.text = trip.degrees
            tripPhoto.loadUrl(trip.photo)
            itemView.setOnClickListener(View.OnClickListener {
                /*Toast.makeText(this, trip.zone , Toast.LENGTH_SHORT).show() */
            })
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}