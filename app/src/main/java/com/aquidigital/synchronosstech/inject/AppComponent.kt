package com.aquidigital.synchronosstech.inject

import android.app.Application
import com.aquidigital.synchronosstech.App
import com.aquidigital.synchronosstech.repository.remote.ApiModule
import com.aquidigital.synchronosstech.repository.cache.CacheModule
import com.aquidigital.synchronosstech.location.LocationModule
import com.aquidigital.synchronosstech.syncworker.SyncWorkerModule
import com.aquidigital.synchronosstech.ui.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    LocationModule::class,
    ApiModule::class,
    CacheModule::class,
    UiModule::class,
    SyncWorkerModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}