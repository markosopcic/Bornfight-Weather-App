package com.markosopcic.weatherservicelib.apimodels

import com.squareup.moshi.Json


data class APIDetailedWeatherStatus(
    @field:Json(name = "current")
    val current: APICurrent,
    @field:Json(name = "daily")
    val daily: List<APIDaily>,
    @field:Json(name = "hourly")
    val hourly: List<APIHourly>,
    @field:Json(name = "lat")
    val lat: Double,
    @field:Json(name = "lon")
    val lon: Double,
    @field:Json(name = "minutely")
    val minutely: List<APIMinutely>?,
    @field:Json(name = "timezone")
    val timezone: String,
    @field:Json(name = "timezone_offset")
    val timezoneOffset: Int
)

data class APICurrent(
    @field:Json(name = "clouds")
    val clouds: Double,
    @field:Json(name = "dew_point")
    val dewPoint: Double,
    @field:Json(name = "dt")
    val dt: Int,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "humidity")
    val humidity: Double,
    @field:Json(name = "pressure")
    val pressure: Double,
    @field:Json(name = "sunrise")
    val sunrise: Int,
    @field:Json(name = "sunset")
    val sunset: Int,
    @field:Json(name = "temp")
    val temp: Double,
    @field:Json(name = "uvi")
    val uvi: Double,
    @field:Json(name = "visibility")
    val visibility: Double,
    @field:Json(name = "weather")
    val weather: List<APIWeather>,
    @field:Json(name = "wind_deg")
    val windDeg: Double,
    @field:Json(name = "wind_speed")
    val windSpeed: Double
)

data class APIDaily(
    @field:Json(name = "clouds")
    val clouds: Double,
    @field:Json(name = "dew_point")
    val dewPoint: Double,
    @field:Json(name = "dt")
    val dt: Int,
    @field:Json(name = "feels_like")
    val feelsLike: APIFeelsLike,
    @field:Json(name = "humidity")
    val humidity: Double,
    @field:Json(name = "pop")
    val precipitationPercentage: Double,
    @field:Json(name = "pressure")
    val pressure: Double,
    @field:Json(name = "rain")
    val rain: Double,
    @field:Json(name = "sunrise")
    val sunrise: Int,
    @field:Json(name = "sunset")
    val sunset: Int,
    @field:Json(name = "temp")
    val temp: APITemp,
    @field:Json(name = "uvi")
    val uvi: Double,
    @field:Json(name = "weather")
    val weather: List<APIWeather>,
    @field:Json(name = "wind_deg")
    val windDeg: Double,
    @field:Json(name = "wind_speed")
    val windSpeed: Double
)

data class APIFeelsLike(
    @field:Json(name = "day")
    val day: Double,
    @field:Json(name = "eve")
    val eve: Double,
    @field:Json(name = "morn")
    val morn: Double,
    @field:Json(name = "night")
    val night: Double
)

data class APIHourly(
    @field:Json(name = "clouds")
    val clouds: Double,
    @field:Json(name = "dew_point")
    val dewPoint: Double,
    @field:Json(name = "dt")
    val dt: Int,
    @field:Json(name = "feels_like")
    val feelsLike: Double,
    @field:Json(name = "humidity")
    val humidity: Double,
    @field:Json(name = "pop")
    val precipitationPercentage: Double,
    @field:Json(name = "pressure")
    val pressure: Double,
    @field:Json(name = "temp")
    val temp: Double,
    @field:Json(name = "visibility")
    val visibility: Double,
    @field:Json(name = "weather")
    val weather: List<APIWeather>,
    @field:Json(name = "wind_deg")
    val windDeg: Double,
    @field:Json(name = "wind_speed")
    val windSpeed: Double
)

data class APIMinutely(
    @field:Json(name = "dt")
    val dt: Int,
    @field:Json(name = "precipitation")
    val precipitation: Double
)

data class APITemp(
    @field:Json(name = "day")
    val day: Double,
    @field:Json(name = "eve")
    val eve: Double,
    @field:Json(name = "max")
    val max: Double,
    @field:Json(name = "min")
    val min: Double,
    @field:Json(name = "morn")
    val morn: Double,
    @field:Json(name = "night")
    val night: Double
)


