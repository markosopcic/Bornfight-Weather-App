package com.markosopcic.searchlocations.ui

import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.ui.BaseViewModel
import com.markosopcic.locationsource.usecase.GetSavedLocationById
import com.markosopcic.permissionslib.usecase.IsLocationPermissionGranted
import com.markosopcic.permissionslib.usecase.RequestLocationPermission
import com.markosopcic.searchlocations.ui.SearchLocationsViewState.SavedLocationsViewState
import com.markosopcic.userlocationsource.usecase.GetUserLocation
import com.markosopcic.weatherservicelib.usecase.GetWeatherForSavedLocations
import com.markosopcic.weatherservicelib.usecase.LocationWithWeather
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.processors.PublishProcessor

class SearchLocationsViewModel(
    private val getSavedLocationForId: GetSavedLocationById,
    private val requestLocationPermission: RequestLocationPermission,
    private val isLocationPermissionGranted: IsLocationPermissionGranted,
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

    private val userLocationRequestedProcessor = PublishProcessor.create<Unit>()

    init {
        observe(
            getWeatherForSavedLocations()
                .map {
                    it.map { it.mapToViewState() }
                }
                .map(::SavedLocationsViewState))

        execute(userLocationRequestedProcessor
            .switchMap { isLocationPermissionGranted() }
            .flatMapCompletable {
                if (!it) {
                    requestLocationPermission()
                } else {
                    getUserLocation().flatMapCompletable { location ->
                        Completable.fromAction {
                            sendRoutingAction { router -> router.showWeatherDetails(location.name, location.latitude, location.longitude) }
                        }
                    }
                }
            })
    }

    private fun LocationWithWeather.mapToViewState() =
        LocationItemViewState(
            location.id,
            location.name,
            weather.main.temperature.toInt(),
            weather.main.temperatureMin.toInt(),
            weather.main.temperatureMax.toInt(),
            weather.weather.firstOrNull()?.icon
        )

    fun getUsersLocation() = userLocationRequestedProcessor.onNext(Unit)

    fun locationSelected(address: String, latitude: Double, longitude: Double) = sendRoutingAction { it.showWeatherDetails(address, latitude, longitude) }

    fun savedLocationSelected(id: Int) = execute(getSavedLocationForId(id).flatMapCompletable { location ->
        Completable.fromAction {
            sendRoutingAction { it.showWeatherDetails(location.name, location.latitude, location.longitude) }
        }
    })
}
