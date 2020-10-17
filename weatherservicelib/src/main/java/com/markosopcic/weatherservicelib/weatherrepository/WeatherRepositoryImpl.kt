package com.markosopcic.weatherservicelib.weatherrepository

import com.markosopcic.core.retryOnNetworkFailure
import com.markosopcic.weatherservicelib.apimodels.APIWeatherStatus
import com.markosopcic.weatherservicelib.models.*
import com.markosopcic.weatherservicelib.weatherservice.WeatherService
import io.reactivex.Flowable

private const val ICON_URL_TEMPLATE = "http://openweathermap.org/img/wn/%s@2x.png"

class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository {

    override fun queryCurrentWeather(longitude: Double, latitude: Double): Flowable<WeatherStatus> = weatherService.getWeatherForLocation(longitude, latitude)
        .retryOnNetworkFailure()
        .map(::toDomainModel)

    private fun toDomainModel(apiStatus: APIWeatherStatus) = with(apiStatus) {
        WeatherStatus(
            base,
            id,
            with(coordinates) { Coordinates(latitude, longitude) },
            timestamp,
            id,
            with(main) { Main(humidity, pressure, temperature.kelvinToCelsius(), temperatureMax.kelvinToCelsius(), temperatureMin.kelvinToCelsius()) },
            name,
            with(general) { General(country, sunrise, sunset, type) },
            visibility,
            weather.map { Weather(it.description, ICON_URL_TEMPLATE.format(it.icon), it.id, it.main) },
            with(wind) { Wind(degree, speed) }
        )
    }

    private fun Double.kelvinToCelsius() = this - 273.15
}
