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

   /* lateinit var mRecyclerView : RecyclerView
    val mAdapter : RecyclerAdapterDescubre = RecyclerAdapterDescubre()
*/

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

       /* setUpRecyclerView()*/

    }


   /* private fun setUpRecyclerView(){
        mRecyclerView = findViewById(R.id.rvDescrubre) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.RecyclerAdapterDescubre(getDataTrips())
        mRecyclerView.adapter = mAdapter
    }

    private fun getDataTrips() : MutableList<Trips>{

        var trips: MutableList<Trips> = ArrayList()
        var users: MutableList<User> = ArrayList()
        users.add(User("AAA","AAA","AAA","AAA"))
        trips.add(Trips("Boulder","Albarracín", "19 Sept", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",2, users))
        trips.add(Trips("Clasica","Pico de la miel", "12 Sept", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",5, users))
        trips.add(Trips("Desportiva","Rodellar", "21 Oct", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",3, users))
        trips.add(Trips("Boulder","Tamajon", "19 Ene", "https://cursokotlin.com/wp-content/uploads/2017/07/spiderman.jpg","16ºC",1, users))

        return trips
    }*/

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

    /*private fun setup(email: String){
        //Toast.makeText(this, email, Toast.LENGTH_LONG).show()
        logout.setOnClickListener{
            logout()
        }
    }*/

    /*//Logout de la aplicación
    private fun logout(){
        FirebaseAuth.getInstance().signOut()
        val authIntent = Intent(this, AuthActivity::class.java).apply {
        }
        startActivity(authIntent)
    }*/
