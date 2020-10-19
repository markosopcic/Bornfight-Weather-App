package com.markosopcic.weatherservicelib.models

data class Current(
    val clouds: Int,
    val dewPoint: Double,
    val datetimeSeconds: Int,
    val feelsLike: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val windDeg: Int,
    val windSpeed: Double
)
