package com.example.climberapp.ui.session

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.climberapp.R
import com.example.climberapp.ui.classLayer.User
import com.example.climberapp.ui.shared.ActivityCalls
import com.google.firebase.auth.FirebaseAuth

class SessionManagement{
    private val myContext: Context
    private val shareds =  ActivityCalls()

    constructor(context: Context) {
        this.myContext = context
    }

    //Guarda los datos de la sesion en un objeto de tipo SharedPreferences.
    fun saveSessionData(user: User) {
        val prefs: SharedPreferences.Editor = myContext.getSharedPreferences( R.string.prefs_file.toString(), AppCompatActivity.MODE_PRIVATE).edit()

        prefs.putString("name", user.name)
        prefs.putString("email", user.email)
        prefs.putString("uid", user.uid)
        prefs.putString("phone", user.phone)
        prefs.apply()
    }

    //Borra los datos de la sesion y envia al usuario al Authenticator
    fun logout(context: Context) {
        val prefs: SharedPreferences.Editor = myContext.getSharedPreferences( R.string.prefs_file.toString(), AppCompatActivity.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        FirebaseAuth.getInstance().signOut()
        shareds.goToAuthActivity(context)
    }

    //Comprueba que no hay una sesion activa
    fun isActive(context: Context){
        val prefs: SharedPreferences = myContext.getSharedPreferences( R.string.prefs_file.toString(), AppCompatActivity.MODE_PRIVATE)
        val name = prefs.getString("name", null)
        val email = prefs.getString("email", null)
        val uid = prefs.getString("uid", null)
        val phone = prefs.getString("phone", null)
        val user = User(uid, name, email,phone)

        if(name != null && email != null && uid != null){
            shareds.goToHomeActivity(user, context)
        }
    }

}