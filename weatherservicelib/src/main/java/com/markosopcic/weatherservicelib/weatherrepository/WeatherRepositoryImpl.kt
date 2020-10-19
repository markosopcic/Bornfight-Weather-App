package com.markosopcic.weatherservicelib.weatherrepository

import com.markosopcic.core.retryOnNetworkFailure
import com.markosopcic.weatherservicelib.apimodels.*
import com.markosopcic.weatherservicelib.models.*
import com.markosopcic.weatherservicelib.weatherservice.WeatherService
import io.reactivex.Flowable

private const val ICON_URL_TEMPLATE = "https://openweathermap.org/img/wn/%s@2x.png"

class WeatherRepositoryImpl(private val weatherService: WeatherService) : WeatherRepository {

    override fun queryCurrentWeather(longitude: Double, latitude: Double): Flowable<WeatherStatus> = weatherService.getWeatherForLocation(longitude, latitude)
        .retryOnNetworkFailure()
        .map(::toDomainModel)

    override fun queryDetailedWeather(longitude: Double, latitude: Double): Flowable<DetailedWeatherStatus> = weatherService.getDetailedWeatherForLocation(longitude, latitude)
        .retryOnNetworkFailure()
        .map(::toDomainModel)

    private fun toDomainModel(apiStatus: APIDetailedWeatherStatus) = with(apiStatus) {
        DetailedWeatherStatus(
            current.toDomainModel(),
            daily.map { it.toDomainModel() },
            hourly.map { it.toDomainModel() },
            lat,
            lon,
            minutely?.map { it.toDomainModel() },
            timezone,
            timezoneOffset
        )
    }

    private fun APIMinutely.toDomainModel() = Minutely(dt, precipitation)

    private fun APIHourly.toDomainModel() = Hourly(clouds, dewPoint, dt, feelsLike.kelvinToCelsius(), humidity, precipitationPercentage.toPercentage(), pressure, temp.kelvinToCelsius(), visibility, weather.map { it.toDomainModel() }, windDeg, windSpeed)

    private fun APIDaily.toDomainModel() = Daily(clouds, dewPoint, dt, feelsLike.toDomainModel(), humidity, precipitationPercentage.toPercentage(), pressure, rain, sunrise, sunset, temp.toDomainModel(), uvi, weather.map { it.toDomainModel() }, windDeg, windSpeed)

    private fun APIWeather.toDomainModel() = Weather(description, ICON_URL_TEMPLATE.format(icon), id, main)

    private fun APITemp.toDomainModel() = Temperature(day.kelvinToCelsius(), eve.kelvinToCelsius(), max.kelvinToCelsius(), min.kelvinToCelsius(), morn.kelvinToCelsius(), night.kelvinToCelsius())

    private fun APIFeelsLike.toDomainModel() = FeelsLike(day.kelvinToCelsius(), eve.kelvinToCelsius(), morn.kelvinToCelsius(), night.kelvinToCelsius())

    private fun APICurrent.toDomainModel() =
        Current(clouds, dewPoint, dt, feelsLike.kelvinToCelsius(), humidity, pressure, sunrise, sunset, temp.kelvinToCelsius(), uvi, visibility, weather.map { it.toDomainModel() }, windDeg, windSpeed)

    private fun toDomainModel(apiStatus: APIWeatherStatus) = with(apiStatus) {
        WeatherStatus(
            base,
            id,
            with(coordinates) { Coordinates(latitude, longitude) },
            timestamp,
            id,
            with(main) { Main(humidity, pressure, temperature.kelvinToCelsius(), temperatureMax.kelvinToCelsius(), temperatureMin.kelvinToCelsius(), feelsLikeTemp.kelvinToCelsius()) },
            name,
            with(general) { General(country, sunrise, sunset, type) },
            visibility,
            weather.map { it.toDomainModel() },
            with(wind) { Wind(degree, speed) }
        )
    }

    private fun Double.toPercentage() = (this * 100).toInt()

    private fun Double.kelvinToCelsius() = this - 273.15
}
