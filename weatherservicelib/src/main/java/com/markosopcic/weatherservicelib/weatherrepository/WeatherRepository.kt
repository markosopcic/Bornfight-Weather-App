package com.markosopcic.weatherservicelib.weatherrepository

import com.markosopcic.weatherservicelib.models.WeatherStatus
import io.reactivex.Flowable

interface WeatherRepository {

    fun queryCurrentWeather(longitude: Double, latitude: Double): Flowable<WeatherStatus>
}
