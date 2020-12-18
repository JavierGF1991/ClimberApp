package com.example.climberapp.ui.shared

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.climberapp.ui.auth.AuthActivity
import com.example.climberapp.ui.home.HomeActivity
import com.example.climberapp.ui.classLayer.User

class ActivityCalls {

    fun goToHomeActivity(user: User, context: Context) {
        val intent = Intent(context, HomeActivity::class.java)
        intent.putExtra("name", user.name)
        intent.putExtra("email", user.email)
        intent.putExtra("uid", user.uid)
        intent.putExtra("phone", user.phone)
        context.startActivity(intent) //Lanza la nueva actividad
        (context as Activity).finish() //El contexto como actividad termina

    }

    fun goToAuthActivity(context: Context) {
        val intent = Intent(context, AuthActivity::class.java)
        context.startActivity(intent) //Lanza la nueva actividad
        (context as Activity).finish() //El contexto como actividad termina
    }
}