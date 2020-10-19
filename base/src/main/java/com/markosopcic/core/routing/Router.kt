package com.markosopcic.core.routing

import com.markosopcic.core.routing.config.WeatherDetailsConfig

interface Router {
    fun showSearchLocationsScreen()

    fun requestPermissions(permissions: Array<String>)

    fun showWeatherDetails(config: WeatherDetailsConfig)

    fun showEnableLocationScreen()

    fun goBack()

}
