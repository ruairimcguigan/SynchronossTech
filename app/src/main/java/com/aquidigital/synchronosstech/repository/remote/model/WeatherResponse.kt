package com.aquidigital.synchronosstech.repository.remote.model

import androidx.room.PrimaryKey

data class WeatherResponse(
    var dt: Int,
    var coord: Coord,
    var weather: List<Weather>,
    var name: String,
    var cod: Int,
    var main: Main,
    var clouds: Clouds,
    var sys: Sys,
    var base: String,
    var wind: Wind
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}