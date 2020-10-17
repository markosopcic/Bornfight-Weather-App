package com.markosopcic.weatherservicelib.usecase

import com.markosopcic.core.usecase.QueryUseCaseWithParam
import com.markosopcic.locationsource.model.Location
import com.markosopcic.weatherservicelib.models.WeatherStatus
import com.markosopcic.weatherservicelib.weatherrepository.WeatherRepository
import io.reactivex.Flowable

class GetWeatherForLocation(private val weatherRepository: WeatherRepository) : QueryUseCaseWithParam<Location, WeatherStatus> {

    override fun invoke(param: Location): Flowable<WeatherStatus> = weatherRepository.queryCurrentWeather(param.longitude, param.latitude)
}
