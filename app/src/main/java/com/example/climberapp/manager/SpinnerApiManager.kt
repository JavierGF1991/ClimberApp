package com.example.climberapp.manager

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import com.example.climberapp.ui.adapter.RecyclerAdapterDescubre
import com.example.climberapp.ui.classLayer.Trip
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore

class SpinnerApiManager {

    private val db = FirebaseFirestore.getInstance()

    fun getComunidades(): MutableList<String>{
        var comunidades = ArrayList<String>()
        var Trips = ArrayList<String>()
        comunidades.clear()
        comunidades.add("Cualquier provincia")
        var count: Int = 0
        db.collection("Comunidades").orderBy("Nombre").get().addOnSuccessListener { documents ->
                for (document in documents) {
                    db.collection("Trips").whereEqualTo("city", (document["Nombre"] as String?).toString()).get().addOnSuccessListener { documentsCity ->
                        for (documentCity in documentsCity) {
                            count++
                        }
                        if(count == 0){
                            comunidades.add((document["Nombre"] as String?).toString() + "("+count+")")
                        }else{
                            comunidades.add((document["Nombre"] as String?).toString() + "("+count+")")
                        }
                        count=0
                        Log.e("comunidades",  (document["Nombre"] as String?).toString() + "("+count+")")
                        }
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents: ", exception)
                        }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

        return comunidades
    }
}