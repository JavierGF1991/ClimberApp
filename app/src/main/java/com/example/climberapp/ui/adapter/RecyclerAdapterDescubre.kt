package com.example.climberapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.climberapp.R
import com.example.climberapp.ui.classLayer.Trip
import com.google.firebase.Timestamp
import com.squareup.picasso.Picasso
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class RecyclerAdapterDescubre : RecyclerView.Adapter<RecyclerAdapterDescubre.ViewHolder>() {

    private lateinit var trip: MutableList<Trip>

    fun RecyclerAdapterDescubre(trip: MutableList<Trip>){
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

        fun bind(trip: Trip){
            tripType.text = trip.type + " en"
            tripZone.text = trip.zone+ ", "
            tripDate.text = getDateTime(trip.date)
            tripPeople.text = trip.people
            tripDegrees.text = trip.degrees
            tripPhoto.loadUrl(trip.photo)
            tripPhoto.setOnClickListener{
                getDateTime(trip.date)
            }


        }
        private fun ImageView.loadUrl(url: String?){
            Picasso.with(context).load(url).error(R.drawable.kennethbarker).resize(111, 111).centerCrop().into(
                this
            )
        }

        private fun getDateTime(s: Timestamp): String {
            val spanish = Locale("es", "ES")
            val millis: Long = s.seconds * 1000
            val date = Date(millis)
            val sdf = SimpleDateFormat("d MMMM", spanish)
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val formattedDate: String = sdf.format(date)
            val strs = formattedDate.split(" ").toTypedArray()
            return changeDate(strs)
        }
        private fun changeDate(date: Array<String>) : String{
            if (date[1] == "enero")
                date[1] = "Ene"
            if (date[1] == "febrero")
                date[1] = "Feb"
            if (date[1] == "marzo")
                date[1] = "Mar"
            if (date[1] == "abril")
                date[1] = "Abri"
            if (date[1] == "mayo")
                date[1] = "May"
            if (date[1] == "junio")
                date[1] = "Jun"
            if (date[1] == "julio")
                date[1] = "Jul"
            if (date[1] == "agosto")
                date[1] = "Ago"
            if (date[1] == "septiembre")
                date[1] = "Sep"
            if (date[1] == "octubre")
                date[1] = "Oct"
            if (date[1] == "noviembre")
                date[1] = "Nov"
            if (date[1] == "diciembre")
                date[1] = "Dic"

            return date[0] +" "+ date[1]


        }
    }
}