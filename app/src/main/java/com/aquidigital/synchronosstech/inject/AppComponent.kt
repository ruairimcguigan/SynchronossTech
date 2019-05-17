package com.aquidigital.synchronosstech.inject

import com.aquidigital.synchronosstech.App
import com.aquidigital.synchronosstech.location.LocationModule
import com.aquidigital.synchronosstech.repository.cache.CacheModule
import com.aquidigital.synchronosstech.repository.remote.ApiModule
import com.aquidigital.synchronosstech.syncworker.SyncWorkerModule
import com.aquidigital.synchronosstech.ui.UiModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    LocationModule::class,
    ApiModule::class,
    CacheModule::class,
    UiModule::class,
    SyncWorkerModule::class
])
internal interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}