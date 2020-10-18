package com.markosopcic.core.routing

interface Router {
    fun showSearchLocationsScreen()

    fun requestPermissions(permissions: Array<String>)

    fun showWeatherDetails(address: String, latitude: Double, longitude: Double)
}
