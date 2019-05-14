package com.aquidigital.synchronosstech.repository.cache

import android.app.Application
import androidx.room.Room
import com.aquidigital.synchronosstech.repository.CacheConstants.DB_NAME
import dagger.Module
import dagger.Provides

@Module
object CacheModule {

    @JvmStatic
    @Provides
    fun provideDatabase(application: Application): WeatherDb {
        return Room.databaseBuilder(
            application.applicationContext,
            WeatherDb::class.java,
            DB_NAME
        ).build()
    }

    @JvmStatic
    @Provides
    fun provideAlbumDao(db: WeatherDb): WeatherDao = db.weatherDao()

}