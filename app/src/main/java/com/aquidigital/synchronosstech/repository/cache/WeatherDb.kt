package com.aquidigital.synchronosstech.repository.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(WeatherTypeConverter::class)
abstract class WeatherDb: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}