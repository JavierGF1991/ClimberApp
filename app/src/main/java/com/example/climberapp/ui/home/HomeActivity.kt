package com.example.climberapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.climberapp.R
import com.example.climberapp.ui.adapter.RecyclerAdapterDescubre
import com.example.climberapp.ui.classLayer.Trip
import com.example.climberapp.ui.classLayer.User
import com.example.climberapp.ui.models.Trips
import com.example.climberapp.ui.session.SessionManagement
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList


class HomeActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private var user: User? = null
    private val session =  SessionManagement(this)

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
    }

    private fun getUserFromIntent(): User? {
        val name: String? = intent.getStringExtra("name")
        val email: String? = intent.getStringExtra("email")
        val uid: String? = intent.getStringExtra("uid")
        val phone: String? = intent.getStringExtra("phone")
        val user = User(uid, name, email,phone)
        session.saveSessionData(user)

        return user
    }

    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }
}
