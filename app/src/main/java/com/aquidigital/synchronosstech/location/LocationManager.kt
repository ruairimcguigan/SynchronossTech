package com.aquidigital.synchronosstech.location

import android.location.LocationListener
import android.location.LocationManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import javax.inject.Inject

class LocationManager@Inject constructor(
    private val locationHelper: LocationHelper){

    fun bindLocationListener(
        lifecycleOwner: LifecycleOwner,
        listener: LocationListener) {

        BoundLocationListener(
            lifecycleOwner,
            locationHelper,
            listener)
    }

    internal class BoundLocationListener(
        lifecycleOwner: LifecycleOwner,
        val locationHelper: LocationHelper,
        private val locationListener: LocationListener) : LifecycleObserver {

        private var locationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun getLastLocation() {
            locationHelper.getLastLocation(locationListener)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            locationManager!!.removeUpdates(locationListener)
            locationManager = null
        }
    }
}