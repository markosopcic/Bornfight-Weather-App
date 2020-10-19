package com.markosopcic.weatherservicelib.models

data class Hourly(
    val clouds: Int,
    val dewPoint: Double,
    val timestampSeconds: Int,
    val feelsLike: Double,
    val humidity: Int,
    val precipitationPercentage: Int,
    val pressure: Int,
    val temperature: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val windDegrees: Int,
    val windSpeed: Double
)
