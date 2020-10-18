package com.markosopcic.weather.routing

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.markosopcic.core.routing.BaseRouter
import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.addFragment
import com.markosopcic.searchlocations.ui.SearchLocationsFragment
import com.markosopcic.weather.R
import com.markosopcic.weatherdetails.ui.WeatherDetailsFragment

private const val MAIN_CONTAINER = R.id.fragment_container

class RouterImpl(private val activity: AppCompatActivity, private val fragmentManager: FragmentManager) : BaseRouter(activity, fragmentManager), Router {

    override fun showSearchLocationsScreen() = fragmentManager.addFragment(MAIN_CONTAINER, SearchLocationsFragment())

    override fun requestPermissions(permissions: Array<String>) = ActivityCompat.requestPermissions(activity, permissions, 0)

    override fun showWeatherDetails(address: String, latitude: Double, longitude: Double) =
        fragmentManager.addFragment(MAIN_CONTAINER, WeatherDetailsFragment.getInstance(address, latitude, longitude))

}
