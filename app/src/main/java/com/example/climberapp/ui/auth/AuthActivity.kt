package com.example.climberapp.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.climberapp.R
import com.example.climberapp.ui.classLayer.User
import com.example.climberapp.ui.session.SessionManagement
import com.example.climberapp.ui.shared.ActivityCalls
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.shobhitpuri.custombuttons.GoogleSignInButton


class AuthActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 100

    private lateinit var authViewModel: AuthViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private val session =  SessionManagement(this)
    private val shareds =  ActivityCalls()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        session.isActive(this) //Comprueba que no hay una sesion activa

        initSignInButton()
        initAuthViewModel()
        initGoogleSignInClient()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val googleSignInAccount = task.getResult(
                    ApiException::class.java
                )
                googleSignInAccount?.let { getGoogleAuthCredential(it) }
            } catch (e: ApiException) {
                Log.e("Error onActivityResult", e.message.toString())
            }
        }
    }

    //Instancia Boton inicia de sesión
    private fun initSignInButton() {
        val googleSignInButton = findViewById<GoogleSignInButton>(R.id.googleButton)
        googleSignInButton.setOnClickListener { signIn() }
    }

    private fun initAuthViewModel() {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
    }

    //Inicia Cliente de inicio de sesión de Google
    private fun initGoogleSignInClient() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
    }

    //Inicio de sesion
    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    //Obtener la credencial de autorización de Google
    private fun getGoogleAuthCredential(googleSignInAccount: GoogleSignInAccount) {
        val googleTokenId = googleSignInAccount.idToken
        val googleAuthCredential = GoogleAuthProvider.getCredential(googleTokenId, null)
        signInWithGoogleAuthCredential(googleAuthCredential)
    }

    //Iniciar sesión con la credencial de autorización de Google
    private fun signInWithGoogleAuthCredential(googleAuthCredential: AuthCredential) {
        authViewModel.signInWithGoogle(googleAuthCredential)
        authViewModel.authenticatedUserLiveData?.observe(this) { authenticatedUser ->
            if (authenticatedUser.isNew) {
                createNewUser(authenticatedUser)
            } else {
                shareds.goToHomeActivity(authenticatedUser,this)
            }
        }
    }

    //CReacion de un nuevo Usuario
    private fun createNewUser(authenticatedUser: User) {
        authViewModel.createUser(authenticatedUser)
        authViewModel.createdUserLiveData?.observe(this) { user ->
            if (user.isCreated) {
                Toast.makeText(this, user.name, Toast.LENGTH_SHORT).show()
            }
            shareds.goToHomeActivity(user,this)
        }
    }

    /*Llama a la Actividad principal
    fun goToMainActivity(user: User) {
        val intent = Intent(this@AuthActivity, HomeActivity::class.java)
        intent.putExtra("name", user.name)
        intent.putExtra("email", user.email)
        intent.putExtra("uid", user.uid)
        intent.putExtra("phone", user.phone)
        startActivity(intent)
        finish()
    }*/
}