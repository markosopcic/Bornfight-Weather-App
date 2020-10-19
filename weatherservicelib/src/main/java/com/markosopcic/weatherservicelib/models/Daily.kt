package com.markosopcic.weatherservicelib.models

data class Daily(
    val clouds: Int,
    val dewPoint: Double,
    val timestampSeconds: Int,
    val feelsLike: FeelsLike,
    val humidity: Int,
    val precipitationPercentage: Int,
    val pressure: Int,
    val rain: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temperature,
    val uvi: Double,
    val weather: List<Weather>,
    val windDeg: Int,
    val windSpeed: Double
)
