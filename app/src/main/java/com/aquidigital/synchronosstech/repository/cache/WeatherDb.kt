package com.aquidigital.synchronosstech.repository.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false)
abstract class WeatherDb: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}