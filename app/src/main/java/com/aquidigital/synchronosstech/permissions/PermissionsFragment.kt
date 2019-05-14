package com.aquidigital.synchronosstech.permissions

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aquidigital.synchronosstech.R
import com.aquidigital.synchronosstech.ui.weather.WeatherActivity
import com.aquidigital.synchronosstech.ui.util.toast
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class PermissionsFragment : Fragment() {

    private val PERMISSIONS_REQUEST_CODE = 100
    private val permissions = arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        if (!hasPermissions()) {
            requestPermissions(
                permissions,
                PERMISSIONS_REQUEST_CODE)
        } else {
            navigateToWeatherFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        showWeatherFragmentOnPermissionsGranted()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size <= 0) run {
                Timber.i(
                    javaClass.simpleName, "User interaction was cancelled."
                )
            } else if (grantResults[0] == PERMISSION_GRANTED) {
                navigateToWeatherFragment()
            } else {
                if (!shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                    (activity as WeatherActivity).showPermissionSettingsSnack(
                        getString(R.string.location__permission_request),
                        Snackbar.LENGTH_INDEFINITE
                    )
                } else {
                    inflatePermissionRationaleAlertDialog()
                }
            }
        }
    }

    private fun inflatePermissionRationaleAlertDialog() {
        activity?.toast(activity!!.getString(R.string.location_request_rationale_dialog))
        requestLocationPermission()
    }

    private fun showWeatherFragmentOnPermissionsGranted() {
        if (hasPermissions()) {
            navigateToWeatherFragment()
        }
    }

    private fun hasPermissions(): Boolean {
        for (permission in permissions) {
            if (activity?.checkSelfPermission(permission) != PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    private fun navigateToWeatherFragment() {
        findNavController().navigate(R.id.show_weather)
    }
}
