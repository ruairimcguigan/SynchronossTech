package com.aquidigital.synchronosstech.ui.weather

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aquidigital.synchronosstech.BuildConfig.APPLICATION_ID
import com.aquidigital.synchronosstech.R
import com.aquidigital.synchronosstech.R.id
import com.aquidigital.synchronosstech.R.layout
import com.google.android.material.snackbar.Snackbar.make
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_weather.*
import javax.inject.Inject

class WeatherActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_weather)
        setNavController()
    }

    fun showPermissionSettingsSnack(message: String, duration: Int) {

        val snack = make(root, message, duration)
        snack.setAction(getString(R.string.settings)) {
            allowPermissionSettingsAdjustmentOnDenial()
        }
        snack.show()
    }

    private fun setNavController() {
        navController = Navigation.findNavController(this, id.navHostFragment)
    }

    private fun allowPermissionSettingsAdjustmentOnDenial() {
        val intent = Intent()
        intent.action = ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", APPLICATION_ID, null)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
