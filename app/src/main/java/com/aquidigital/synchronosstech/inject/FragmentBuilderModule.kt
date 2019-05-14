package com.aquidigital.synchronosstech.inject

import com.aquidigital.synchronosstech.ui.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesWeatherFragment(): WeatherFragment
}