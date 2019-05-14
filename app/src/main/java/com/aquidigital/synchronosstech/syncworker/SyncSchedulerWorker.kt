package com.aquidigital.synchronosstech.syncworker

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.aquidigital.synchronosstech.location.LocationHelper
import com.aquidigital.synchronosstech.repository.WeatherRepository
import timber.log.Timber
import javax.inject.Inject

class SyncSchedulerWorker(
    val repo: WeatherRepository,
    val locationHelper: LocationHelper,
    context: Context,
    workerParams: WorkerParameters) : Worker(context, workerParams) {

    private val locationListener = MyLocationListener()

    override fun doWork(): Result {
        return try {

            startServerSync()
            Result.success()

        } catch (e: Exception) {
            Result.failure()
        }
    }

    private fun startServerSync() {
        Timber.d("Sync started")
        locationHelper.getLastLocation(locationListener)
    }

    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            Timber.d("Location received: fetching weather data")
            repo.flushDb()
            repo.fetchWeather(location)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    class Factory @Inject constructor(
        private val repo: WeatherRepository,
        private val locationHelper: LocationHelper
        ): ChildWorkerFactory {

        override fun create(appContext: Context, params: WorkerParameters): Worker {
            return SyncSchedulerWorker(repo, locationHelper, appContext, params)
        }
    }
}