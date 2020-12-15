package com.example.climberapp.ui.auth

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.climberapp.ui.pojos.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


internal class AuthRepository {
    private val firebaseAuth = FirebaseAuth.getInstance()
    fun firebaseSignInWithGoogle(googleAuthCredential: AuthCredential?): MutableLiveData<User> { val authenticatedUserMutableLiveData = MutableLiveData<User>()
        firebaseAuth.signInWithCredential(googleAuthCredential!!)
            .addOnCompleteListener { authTask: Task<AuthResult> ->
                if (authTask.isSuccessful) {
                    val isNewUser = authTask.result!!.additionalUserInfo!!.isNewUser
                    val firebaseUser = firebaseAuth.currentUser
                    if (firebaseUser != null) {
                        val uid = firebaseUser.uid
                        val name = firebaseUser.displayName
                        val email = firebaseUser.email
                        val phone = firebaseUser.phoneNumber
                        val user = User(uid, name, email, phone)
                        user.isNew = isNewUser
                        authenticatedUserMutableLiveData.value = user
                    }
                } else {
                    Log.e("Error onActivityResult", authTask.exception!!.message.toString())
                    /*logErrorMessage(authTask.exception!!.message)*/
                }
            }
        return authenticatedUserMutableLiveData
    }
}