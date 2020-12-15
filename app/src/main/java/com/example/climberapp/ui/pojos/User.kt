package com.example.climberapp.ui.pojos

import android.net.Uri
import com.google.firebase.firestore.Exclude


class User  {
    var uid: String? = null
    var name: String? = null
    var email: String? = null
    var phone: String? = null

    @Exclude
    var isAuthenticated = false

    @Exclude
    var isNew = false

    @Exclude
    var isCreated = false

    constructor() {}
    internal constructor(uid: String?, name: String?, email: String?, phone: String?) {
        this.uid = uid
        this.name = name
        this.email = email
        this.phone = phone

    }
}