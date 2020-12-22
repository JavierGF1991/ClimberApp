package com.example.climberapp.manager

import com.example.climberapp.ui.classLayer.User
import com.google.firebase.firestore.FirebaseFirestore

class UsersApiManager {

    private val db = FirebaseFirestore.getInstance()

    constructor()

    //Insertamos el usuario
    fun post(uid: String?, name: String?, email: String?, phone: String?){
        db.collection("Users").document(email.toString()).set(hashMapOf("uid" to uid,"name" to name,"email" to email,"phone" to phone,"phone" to phone))
    }

    //Actualizamos el usuario
    fun put(uid: String?, name: String?, email: String?, phone: String?){
        db.collection("Users").document(email.toString()).set(hashMapOf("uid" to uid,"name" to name,"email" to email,"phone" to phone,"phone" to phone))
    }

    //Devlvemos el usuario logueado
    fun get(email: String?): User {

        var user = User()

        db.collection("Users").document(email.toString()).get().addOnSuccessListener {
            user.email = email
            user.name = it.get("name")as String?
            user.phone = it.get("phone")as String?
        }
        return user

    }

    //Eliminamos el Usuario
    fun delete(email: String?){
        var user = User()
        db.collection("Users").document(email.toString()).delete()
    }
}