package com.aquidigital.synchronosstech.repository

object CacheConstants {

    // db config
    const val DB_NAME = "weather.db"
    const val TABLE_NAME = "WeatherEntity"

    // remote
    const val UNITS = "metric"

    // queries
    const val QUERY_WEATHER = "SELECT * FROM $TABLE_NAME"
    const val DELETE_WEATHER = "DELETE FROM $TABLE_NAME"
}