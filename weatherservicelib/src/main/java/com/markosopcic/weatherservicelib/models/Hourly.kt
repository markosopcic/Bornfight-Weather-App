package com.markosopcic.weatherservicelib.models

data class Hourly(
    val clouds: Double,
    val dewPoint: Double,
    val timestampSeconds: Int,
    val feelsLike: Double,
    val humidity: Double,
    val precipitationPercentage: Double,
    val pressure: Double,
    val temperature: Double,
    val visibility: Double,
    val weather: List<Weather>,
    val windDegrees: Double,
    val windSpeed: Double
)
