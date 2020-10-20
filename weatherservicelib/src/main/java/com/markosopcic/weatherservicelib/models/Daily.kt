package com.markosopcic.weatherservicelib.models

data class Daily(
    val clouds: Double,
    val dewPoint: Double,
    val timestampSeconds: Int,
    val feelsLike: FeelsLike,
    val humidity: Double,
    val precipitationPercentage: Double,
    val pressure: Double,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temperature,
    val uvi: Double,
    val weather: List<Weather>,
    val windDeg: Double,
    val windSpeed: Double
)
