package com.aquidigital.synchronosstech.ui.weather

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aquidigital.synchronosstech.repository.WeatherRepository
import com.aquidigital.synchronosstech.repository.cache.WeatherEntity
import com.aquidigital.synchronosstech.repository.remote.Resource
import javax.inject.Inject

class WeatherViewModel
@Inject constructor(val repo: WeatherRepository) : ViewModel() {
    fun fetchAlbums(location: Location): LiveData<Resource<WeatherEntity>> = repo.fetchWeather(location)
}
