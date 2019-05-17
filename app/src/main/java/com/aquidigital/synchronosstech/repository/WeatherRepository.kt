package com.aquidigital.synchronosstech.repository

import android.app.Application
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.room.Delete
import com.aquidigital.synchronosstech.R
import com.aquidigital.synchronosstech.repository.CacheConstants.UNITS
import com.aquidigital.synchronosstech.repository.cache.WeatherDao
import com.aquidigital.synchronosstech.repository.cache.WeatherEntity
import com.aquidigital.synchronosstech.repository.remote.DataResource
import com.aquidigital.synchronosstech.repository.remote.Resource
import com.aquidigital.synchronosstech.repository.remote.Weather
import com.aquidigital.synchronosstech.repository.remote.WeatherApi
import com.aquidigital.synchronosstech.repository.remote.model.WeatherResponse
import com.aquidigital.synchronosstech.repository.util.AppExecutors
import javax.inject.Inject
import kotlin.concurrent.thread

class WeatherRepository @Inject constructor(
    private val context: Application,
    private val api: WeatherApi,
    private val weatherDao: WeatherDao,
    private val appExecutors: AppExecutors) {

    fun fetchWeather(location: Location): LiveData<Resource<WeatherEntity>> {
        return object : DataResource<WeatherEntity, WeatherResponse>(appExecutors) {

            override fun saveCallResult(weatherData: WeatherResponse) {
                thread {
                    weatherDao.insertWeather(weatherTypeConverter(weatherData))
                }
            }

            override fun shouldFetch(data: WeatherEntity?): Boolean {
                return data == null
            }

            override fun loadFromDb() = weatherDao.getLocalWeather()

            override fun createCall() = api.getCurrentWeatherForLocation(
                location.latitude.toString(),
                location.longitude.toString(),
                context.getString(R.string.api_key),
                UNITS
            )

            override fun onFetchFailed() {

            }
        }.asLiveData()
    }

    private fun weatherTypeConverter(weatherResponse: WeatherResponse): WeatherEntity {
        val data = Weather(weatherResponse)
        return WeatherEntity(
            data.currentTemp(),
            data.maxTemp(),
            data.minTemp(),
            data.humidity(),
            data.iconCode(),
            data.conditionTitle(),
            data.sunrise(),
            data.sunset(),
            data.country(),
            data.wind(),
            data.place()
        )
    }

    @Delete
    fun flushDb() {
        thread {
            weatherDao.deleteWeather()
        }
    }
}