package com.markosopcic.searchlocations.ui

import com.markosopcic.searchlocations.ui.SerarchLocationsViewState.SavedLocationsViewState
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.ui.BaseViewModel
import com.markosopcic.locationsource.usecase.SaveLocation
import com.markosopcic.weatherservicelib.usecase.GetWeatherForSavedLocations
import com.markosopcic.weatherservicelib.usecase.LocationWithWeather
import io.reactivex.Scheduler

class SearchLocationsViewModel(
    private val saveLocation: SaveLocation,
    private val getWeatherForSavedLocations: GetWeatherForSavedLocations,
    backgroundScheduler: Scheduler,
    mainScheduler: Scheduler,
    routingActionSender: RoutingActionSender
) : BaseViewModel<SerarchLocationsViewState>(backgroundScheduler, mainScheduler, routingActionSender) {

    init {
        observe(
            getWeatherForSavedLocations()
                .map {
                    it.map { it.mapToViewState() }
                }
                .map(::SavedLocationsViewState))
    }

    private fun LocationWithWeather.mapToViewState() =
        LocationItemViewState(location.name, weather.main.temperature.toInt(), weather.main.temperatureMin.toInt(), weather.main.temperatureMax.toInt(), weather.weather.firstOrNull()?.icon)

    fun locationSelected(id: String, locationName: String, longitude: Double?, latitude: Double?) {
    }
}
