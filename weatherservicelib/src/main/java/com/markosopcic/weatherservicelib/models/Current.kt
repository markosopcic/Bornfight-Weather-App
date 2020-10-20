package com.markosopcic.weatherservicelib.models

data class Current(
    val clouds: Double,
    val dewPoint: Double,
    val datetimeSeconds: Int,
    val feelsLike: Double,
    val humidity: Double,
    val pressure: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Double,
    val weather: List<Weather>,
    val windDeg: Double,
    val windSpeed: Double
)
