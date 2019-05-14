package com.aquidigital.synchronosstech.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationListener
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import timber.log.Timber
import javax.inject.Inject

class LocationHelper
@Inject constructor(private val context: Context) : LifecycleObserver {

    private lateinit var locationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    fun getLastLocation(
        locationListener: LocationListener) {

        locationClient = getFusedLocationProviderClient(context)

        locationClient.lastLocation
            .addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    locationListener.onLocationChanged(task.result!!)
                } else {
                    Timber.d(task.exception, javaClass.simpleName, "getLastLocation:exception")
                }
            }
    }


}