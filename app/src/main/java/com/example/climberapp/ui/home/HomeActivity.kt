package com.example.climberapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.climberapp.R
import com.example.climberapp.ui.auth.AuthActivity
import com.example.climberapp.ui.pojos.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        navView.setupWithNavController(navController)

        user = getUserFromIntent()
        initGoogleSignInClient()

       /* // Setup
        val bundle = intent.extras
        val email:String? = bundle?.getString("email")
        setup(email?:"" )*/

    }

    private fun getUserFromIntent(): com.example.climberapp.ui.pojos.User? {
        val name: String? = intent.getStringExtra("name")
        val email: String? = intent.getStringExtra("email")
        val uid: String? = intent.getStringExtra("uid")
        val phone: String? = intent.getStringExtra("phone")
        return User(uid, name, email,phone)
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    /*private fun setup(email: String){
        //Toast.makeText(this, email, Toast.LENGTH_LONG).show()
        logout.setOnClickListener{
            logout()
        }
    }*/

    //Logout de la aplicación
    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        val authIntent = Intent(this, AuthActivity::class.java).apply {
        }
        startActivity(authIntent)
    }
}