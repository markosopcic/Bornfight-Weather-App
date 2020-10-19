package com.markosopcic.weatherservicelib.weatherservice

import com.markosopcic.weatherservicelib.apimodels.APIDetailedWeatherStatus
import com.markosopcic.weatherservicelib.apimodels.APIWeatherStatus
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

private const val WEATHER_API_VERSION = 2.5

interface WeatherService {

    @GET("/data/$WEATHER_API_VERSION/weather")
    fun getWeatherForLocation(@Query("lon") longitude: Double, @Query("lat") latitude: Double): Flowable<APIWeatherStatus>

    @GET("/data/$WEATHER_API_VERSION/onecall")
    fun getDetailedWeatherForLocation(@Query("lon") longitude: Double, @Query("lat") latitude: Double): Flowable<APIDetailedWeatherStatus>
}
