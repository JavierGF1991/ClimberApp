package com.example.climberapp.ui.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.climberapp.manager.UsersApiManager
import com.example.climberapp.ui.classLayer.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore



class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private var authRepository: AuthRepository = AuthRepository()
    private val USERS: String = "users"
    var createdUserLiveData: LiveData<User>? = null
    var authenticatedUserLiveData: LiveData<User>? = null
    private val rootRef = FirebaseFirestore.getInstance()
    private val usersRef = rootRef.collection(USERS)

    fun signInWithGoogle(googleAuthCredential: AuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential)
    }

    fun createUser(authenticatedUser: User?) {
        createdUserLiveData = authenticatedUser?.let { createUserInFirestoreIfNotExists(it) }
    }

    private fun createUserInFirestoreIfNotExists(authenticatedUser: User): MutableLiveData<User>? {
        val newUserMutableLiveData = MutableLiveData<User>()
        val uidRef = usersRef.document(authenticatedUser.uid!!)
        uidRef.get().addOnCompleteListener { uidTask: Task<DocumentSnapshot?> ->
            if (uidTask.isSuccessful) {
                val document = uidTask.result
                if (!document!!.exists()) {
                    uidRef.set(authenticatedUser)
                        .addOnCompleteListener { userCreationTask: Task<Void?> ->
                            if (userCreationTask.isSuccessful) {
                                authenticatedUser.isCreated = true
                                newUserMutableLiveData.setValue(authenticatedUser)
                            } else {
                                Log.e(
                                    "Error onActivityResult",
                                    userCreationTask.exception!!.toString()
                                )
                                //logErrorMessage(userCreationTask.exception!!.message)
                            }
                        }
                } else {
                    newUserMutableLiveData.setValue(authenticatedUser)
                }
            } else {
                Log.e("Error CreateUserInFirestoreIfNotExists", uidTask.exception!!.message.toString())
            }
        }
        return newUserMutableLiveData
    }
}