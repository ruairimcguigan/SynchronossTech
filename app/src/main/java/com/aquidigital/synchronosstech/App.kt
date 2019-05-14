package com.aquidigital.synchronosstech

import android.app.Activity
import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import com.aquidigital.synchronosstech.inject.AppInjector
import com.aquidigital.synchronosstech.syncworker.SyncWorkerFactory
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var syncWorkerFactory: SyncWorkerFactory

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
        AppInjector.init(
            this
        )

        WorkManager.initialize(
            this,
            Configuration.Builder()
                .setWorkerFactory(syncWorkerFactory)
                .build()
        )
    }
}