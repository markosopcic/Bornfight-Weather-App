package com.markosopcic.searchlocations.ui

import com.markosopcic.core.publishProgress
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.routing.config.WeatherDetailsConfig
import com.markosopcic.core.ui.BaseViewModel
import com.markosopcic.locationsource.usecase.GetSavedLocationById
import com.markosopcic.permissionslib.usecase.RequireLocationPermissionAndEnabled
import com.markosopcic.searchlocations.ui.SearchLocationsViewState.Loading
import com.markosopcic.searchlocations.ui.SearchLocationsViewState.SavedLocationsViewState
import com.markosopcic.userlocation.usecase.GetUserLocation
import com.markosopcic.weatherservicelib.usecase.GetWeatherForSavedLocations
import com.markosopcic.weatherservicelib.usecase.LocationWithWeather
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.processors.BehaviorProcessor

class SearchLocationsViewModel(
    private val requireLocationPermissionAndEnabled: RequireLocationPermissionAndEnabled,
    private val getSavedLocationForId: GetSavedLocationById,
    private val getUserLocation: GetUserLocation,
    private val getWeatherForSavedLocations: GetWeatherForSavedLocations,

    backgroundScheduler: Scheduler,
    mainScheduler: Scheduler,
    routingActionSender: RoutingActionSender
) : BaseViewModel<SearchLocationsViewState>(
    backgroundScheduler,
    mainScheduler,
    routingActionSender
) {

    private val loadingProcessor: BehaviorProcessor<Boolean> = BehaviorProcessor.createDefault(false)

    init {
        observe(getWeatherForSavedLocations()
            .map {
                it.map(::mapToViewState)
            }
            .map(::SavedLocationsViewState))

        observe(loadingProcessor.map(::Loading))
    }

    private fun mapToViewState(locationAndWeather: LocationWithWeather) =
        with(locationAndWeather) {
            LocationItemViewState(
                location.id!!,
                location.name,
                weather.main.temperature.toInt(),
                weather.weather.firstOrNull()?.icon
            )
        }

    fun getUsersLocation() {
        execute(
            requireLocationPermissionAndEnabled()
                .andThen(
                    getUserLocation().switchMapCompletable { location ->
                        Completable.fromAction {
                            sendRoutingAction { router -> with(location) { router.showWeatherDetails(WeatherDetailsConfig(id, name, latitude, longitude)) } }
                        }
                    }
                )
                .doOnSubscribe {
                    loadingProcessor.onNext(true)
                }
                .publishProgress(loadingProcessor))
    }

    fun locationSelected(address: String, latitude: Double, longitude: Double) = sendRoutingAction { it.showWeatherDetails(WeatherDetailsConfig(null, address, latitude, longitude)) }

    fun savedLocationSelected(id: Int) = execute(getSavedLocationForId(id).flatMapCompletable { location ->
        Completable.fromAction {
            sendRoutingAction { with(location) { it.showWeatherDetails(WeatherDetailsConfig(id, name, latitude, longitude)) } }
        }
    })
}
