package com.example.climberapp.ui.classLayer

import com.google.firebase.Timestamp

class Trip {

    var city: String? = ""
    var type: String? = ""
    var zone: String? = ""
    lateinit var date: Timestamp
    var photo: String? = ""
    var degrees: String? = ""
    var vacancies: String?  = ""
    var people: String? = ""

    constructor(city: String?, type: String?, zone: String?, date: Timestamp, photo: String?, degrees: String?, vacancies: String?, people: String?) {
        this.city = city
        this.type = type
        this.zone = zone
        this.date = date
        this.photo = photo
        this.degrees = degrees
        this.vacancies = vacancies
        this.people = people
    }

    constructor()
}