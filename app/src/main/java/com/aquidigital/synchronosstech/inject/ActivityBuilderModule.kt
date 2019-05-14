package com.aquidigital.synchronosstech.inject

import com.aquidigital.synchronosstech.permissions.PermissionsFragment
import com.aquidigital.synchronosstech.ui.weather.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    internal abstract fun contributesWeatherActivity(): WeatherActivity

    @ContributesAndroidInjector()
    internal abstract fun contributesPermissionsActivity(): PermissionsFragment
}