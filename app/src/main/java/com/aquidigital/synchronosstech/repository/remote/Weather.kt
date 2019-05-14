package com.aquidigital.synchronosstech.repository.remote

import com.aquidigital.synchronosstech.repository.remote.model.Sys
import com.aquidigital.synchronosstech.repository.remote.model.WeatherResponse
import com.aquidigital.synchronosstech.repository.remote.model.Wind
import java.lang.String.valueOf

data class Weather (val weatherResponse: WeatherResponse){

    fun currentTemp(): Double = weatherResponse.main.temp
    fun maxTemp(): Double = weatherResponse.main.temp_max
    fun minTemp(): Double = weatherResponse.main.temp_min
    fun humidity(): String = valueOf(weatherResponse.main.humidity)
    fun iconCode(): String = valueOf(weatherResponse.weather.first().icon)
    fun conditionTitle(): String = weatherResponse.weather.first().main
    fun conditionDescription(): String = weatherResponse.weather.first().description
    fun sunrise(): Long = weatherResponse.sys.sunrise.toLong()
    fun sunset(): Long = weatherResponse.sys.sunset.toLong()
    fun country(): String = weatherResponse.sys.country
    fun sys(): Sys = weatherResponse.sys
    fun wind(): Wind = weatherResponse.wind
    fun place(): String = weatherResponse.name
}