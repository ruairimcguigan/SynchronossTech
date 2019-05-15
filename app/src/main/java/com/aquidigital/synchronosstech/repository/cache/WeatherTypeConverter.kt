package com.aquidigital.synchronosstech.repository.cache

import androidx.room.TypeConverter
import com.aquidigital.synchronosstech.repository.remote.model.Wind
import java.io.Serializable
import java.lang.Double.parseDouble

class WeatherTypeConverter: Serializable{

    @TypeConverter
    fun fromWind(wind: Wind?): String? {
        return if (wind == null) {
            null
        } else String.format(
            "%f,%f", wind.deg, wind.speed
        )
    }

    @TypeConverter
    fun toWind(wind: String?): Wind? {
        if (wind == null) {
            return null
        }
        val pieces = wind.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        return Wind(
            parseDouble(pieces[0]),
            parseDouble(pieces[1])
        )
    }
}


