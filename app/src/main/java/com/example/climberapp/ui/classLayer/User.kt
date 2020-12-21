package com.example.climberapp.ui.classLayer

import com.google.firebase.firestore.Exclude


class User  {

    var  uid: String? = ""
    var  name: String? = ""
    var  email: String? = ""
    var  phone: String? = ""

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