package com.aquidigital.synchronosstech.location

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
internal object LocationModule {

    @JvmStatic
    @Provides
    fun provideLocationManager(locationHelper: LocationHelper): LocationManager {
        return LocationManager(locationHelper)
    }

    @JvmStatic
    @Provides
    fun provideSchedulerLocationHelper(context: Application): LocationHelper {
        return LocationHelper(context)
    }
}