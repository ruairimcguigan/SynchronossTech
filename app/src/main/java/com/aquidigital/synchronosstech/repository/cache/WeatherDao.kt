package com.aquidigital.synchronosstech.repository.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.aquidigital.synchronosstech.repository.CacheConstants.DELETE_WEATHER
import com.aquidigital.synchronosstech.repository.CacheConstants.QUERY_WEATHER

@Dao
interface WeatherDao {

    @Query(QUERY_WEATHER)
    fun getLocalWeather(): LiveData<WeatherEntity>

    @Insert(onConflict = REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity?)

    @Query(DELETE_WEATHER)
    fun deleteWeather()
}