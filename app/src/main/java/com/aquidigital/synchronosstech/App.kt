package com.aquidigital.synchronosstech

import androidx.work.Configuration
import androidx.work.WorkManager
import com.aquidigital.synchronosstech.inject.DaggerAppComponent
import com.aquidigital.synchronosstech.syncworker.SyncWorkerFactory
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject lateinit var syncWorkerFactory: SyncWorkerFactory

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }

        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(syncWorkerFactory)
                .build()
        )
    }

    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder().create(this)
    }
}