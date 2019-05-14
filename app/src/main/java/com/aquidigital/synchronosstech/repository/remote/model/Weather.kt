package com.aquidigital.synchronosstech.repository.remote.model

data class Weather(
    val icon: String,
    val description: String,
    val main: String,
    val id: Int
)