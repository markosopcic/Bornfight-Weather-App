package com.markosopcic.weather.routing

import android.content.Intent
import android.content.pm.PackageManager
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.markosopcic.core.routing.BaseRouter
import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.addFragment
import com.markosopcic.core.routing.config.WeatherDetailsConfig
import com.markosopcic.locationsource.model.Location
import com.markosopcic.searchlocations.ui.SearchLocationsFragment
import com.markosopcic.weather.R
import com.markosopcic.weatherdetails.ui.WeatherDetailsFragment
import kotlin.system.exitProcess

private const val MAIN_CONTAINER = R.id.fragment_container

class RouterImpl(private val activity: AppCompatActivity, private val fragmentManager: FragmentManager) : BaseRouter(activity, fragmentManager), Router {

    override fun showSearchLocationsScreen() = fragmentManager.addFragment(MAIN_CONTAINER, SearchLocationsFragment())

    override fun requestPermissions(permissions: Array<String>) = ActivityCompat.requestPermissions(activity, permissions, 0)

    override fun showWeatherDetails(config: WeatherDetailsConfig) =
        fragmentManager.addFragment(MAIN_CONTAINER, WeatherDetailsFragment.getInstance(config))

    override fun showEnableLocationScreen() {
        activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }

    override fun goBack() {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStackImmediate()
            if (fragmentManager.backStackEntryCount == 0) exitProcess(0)
        } else {
            activity.finish()
        }
    }
}
