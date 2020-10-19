package com.markosopcic.weatherservicelib.usecase

import com.markosopcic.core.usecase.QueryUseCaseWithParam
import com.markosopcic.locationsource.model.Location
import com.markosopcic.weatherservicelib.models.Coordinates
import com.markosopcic.weatherservicelib.models.DetailedWeatherStatus
import com.markosopcic.weatherservicelib.weatherrepository.WeatherRepository
import io.reactivex.Flowable

class GetDetailedWeatherForLocation(private val weatherRepository: WeatherRepository) : QueryUseCaseWithParam<Location, DetailedWeatherStatus> {

    override fun invoke(param: Location): Flowable<DetailedWeatherStatus> = weatherRepository.queryDetailedWeather(param.longitude, param.latitude)
}
