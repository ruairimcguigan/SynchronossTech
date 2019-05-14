package com.aquidigital.synchronosstech.repository.remote

import androidx.lifecycle.LiveData
import com.aquidigital.synchronosstech.repository.cache.WeatherEntity
import com.aquidigital.synchronosstech.repository.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getCurrentWeatherForLocation(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("APPID") appId: String,
        @Query("units") units: String
    ): LiveData<ApiResponse<WeatherResponse>>
}