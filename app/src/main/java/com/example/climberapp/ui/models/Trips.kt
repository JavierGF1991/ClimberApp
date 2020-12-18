package com.example.climberapp.ui.models

import com.example.climberapp.ui.classLayer.User


data class Trips(
    var type: String,
    var zone: String,
    var date: String,
    var photo: String,
    var degrees: String,
    var vacancies: Int,
    val people: MutableList<User>
)