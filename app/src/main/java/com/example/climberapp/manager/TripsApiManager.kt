package com.example.climberapp.manager

import android.content.ContentValues.TAG
import android.util.Log
import com.example.climberapp.ui.adapter.RecyclerAdapterDescubre
import com.example.climberapp.ui.classLayer.Trip
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class TripsApiManager {

    private val db = FirebaseFirestore.getInstance()
    private var trips: MutableList<Trip> = mutableListOf()

    //Devlvemos el usuario logueado
    fun get(mAdapter: RecyclerAdapterDescubre): MutableList<Trip>{
        trips.clear()
        db.collection("Trips").get().addOnSuccessListener { documents ->
                for (document in documents) {
                    trips.add(
                        (Trip(
                            document["city"] as String?,
                            document["type"] as String?,
                            document["zone"] as String?,
                            document["date"] as String?,
                            document["photo"] as String?,
                            document["degrees"] as String?,
                            document["vacancies"] as String?,
                            document["people"] as String?
                        ))
                    )
                }
            mAdapter.notifyDataSetChanged();
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return trips
    }

    fun getByParameters(mAdapter: RecyclerAdapterDescubre, type: String): MutableList<Trip>{
        trips.clear()
        db.collection("Trips").whereEqualTo("type", type).get().addOnSuccessListener { documents ->
            for (document in documents) {
                trips.add(
                    (Trip(
                        document["city"] as String?,
                        document["type"] as String?,
                        document["zone"] as String?,
                        document["date"] as String?,
                        document["photo"] as String?,
                        document["degrees"] as String?,
                        document["vacancies"] as String?,
                        document["people"] as String?
                    ))
                )
            }
            mAdapter.notifyDataSetChanged();
        }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return trips
    }

    //Pediente funcion de una semana
    fun getProximaSemana(mAdapter: RecyclerAdapterDescubre): MutableList<Trip>{
        trips.clear()
        db.collection("Trips").whereEqualTo("date",  sumarRestarDiasFecha() ).get().addOnSuccessListener { documents ->
            for (document in documents) {
                trips.add((Trip(document["city"] as String?, document["type"] as String?, document["zone"] as String?, document["date"] as String?, document["photo"] as String?, document["degrees"] as String?, document["vacancies"] as String?, document["people"] as String?)))
            }
            mAdapter.notifyDataSetChanged();
        }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
        return trips
    }

    fun sumarRestarDiasFecha(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7) // numero de días a añadir, o restar en caso de días<0
        Log.i("fecha", calendar.time.toString())// numero de días a añadir, o restar en caso de días<0)
        return calendar.time
    }

}