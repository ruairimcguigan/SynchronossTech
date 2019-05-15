package com.aquidigital.synchronosstech.ui.weather

import android.content.res.Resources
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.work.*
import com.aquidigital.synchronosstech.R.layout
import com.aquidigital.synchronosstech.R.string.temperature_with_value
import com.aquidigital.synchronosstech.inject.Injectable
import com.aquidigital.synchronosstech.location.LocationManager
import com.aquidigital.synchronosstech.repository.cache.WeatherEntity
import com.aquidigital.synchronosstech.syncworker.SyncSchedulerWorker
import com.aquidigital.synchronosstech.ui.util.UiFormatter
import kotlinx.android.synthetic.main.fragment_weather.*
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WeatherFragment : Fragment(), Injectable {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var locationManager: LocationManager
    @Inject lateinit var formatter: UiFormatter
    @Inject lateinit var res: Resources

    private lateinit var viewModel: WeatherViewModel
    private val locationListener = MyLocationListener()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        subscribeUi()
        bindLocationListener()
        return inflater.inflate(
            layout.fragment_weather,
            container,
            false
        )
    }

    private fun subscribeUi() {
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory).get(
            WeatherViewModel::class.java)
    }

    private fun bindLocationListener() {
        locationManager.bindLocationListener(
            this,
            locationListener
        )
    }

    private inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location) {
            viewModel.fetchAlbums(location).observe(viewLifecycleOwner, Observer { repos ->
                repos.data?.let {
                    displayWeatherData(it)
                    scheduleAndTrackPeriodicSyncJob()
                }
            })
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private fun displayWeatherData(weather: WeatherEntity) {
        cityName.text = weather.place

        overview.text = weather.conditionTitle

        curentTempValue.text = formatter.formatWithMeasurementUnit(
            res,
            temperature_with_value,
            weather.currentTemp
        )

        maxTempValue.text = formatter.formatWithMeasurementUnit(
            res,
            temperature_with_value,
            weather.maxTemp
        )

        minTempValue.text = formatter.formatWithMeasurementUnit(
            res,
            temperature_with_value,
            weather.minTemp
        )

        sunriseValue.text = formatter.epochToDate(weather.sunrise)
        sunsetValue.text = formatter.epochToDate(weather.sunset)
        windValue.text = weather.wind.speed.toString()
    }

    private fun scheduleAndTrackPeriodicSyncJob(){
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest = PeriodicWorkRequest.Builder(
            SyncSchedulerWorker::class.java,
            2,
            TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        Timber.i("SyncSchedulerWorker.WorkerScheduled: schedulePeriodicWork enqueued")
        WorkManager.getInstance().enqueue(workRequest)

        WorkManager.getInstance().getWorkInfoByIdLiveData(UUID.randomUUID())
            .observe(this, Observer { workInfo ->
                if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                    Toast.makeText(activity,"scheduled task to sync with server completed!",Toast.LENGTH_SHORT).show();

                }
            })
    }
}
