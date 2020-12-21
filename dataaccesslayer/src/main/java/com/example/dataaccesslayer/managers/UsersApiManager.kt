package com.example.dataaccesslayer.managers

import com.google.firebase.firestore.FirebaseFirestore

class UsersApiManager {

    private val db = FirebaseFirestore.getInstance()

    constructor()

    fun post(uid: String?, name: String?, email: String?, phone: String?, photo: String?){
        db.collection("Users").document("email").set(hashMapOf("uid" to uid,"name" to name,"enamil" to email,"phone" to phone,"photo" to phone, "photo" to photo))
    }

}