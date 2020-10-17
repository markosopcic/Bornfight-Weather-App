package com.markosopcic.weatherservicelib.usecase

import com.markosopcic.core.usecase.QueryUseCase
import com.markosopcic.locationsource.model.Location
import com.markosopcic.locationsource.usecase.GetSavedLocations
import com.markosopcic.weatherservicelib.models.WeatherStatus
import io.reactivex.Flowable
import io.reactivex.Observable

class GetWeatherForSavedLocations(private val getSavedLocations: GetSavedLocations, private val getWeatherForLocation: GetWeatherForLocation) : QueryUseCase<List<LocationWithWeather>> {

    override fun invoke(): Flowable<List<LocationWithWeather>> = getSavedLocations()
        .flatMapSingle { locations ->
            Observable.merge(locations.map { location ->
                getWeatherForLocation(location)
                    .toObservable()
                    .map { LocationWithWeather(location, it) }
            })
                .toList()
        }
}


data class LocationWithWeather(val location: Location, val weather: WeatherStatus)
