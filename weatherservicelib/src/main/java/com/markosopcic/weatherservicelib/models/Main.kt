package com.markosopcic.weatherservicelib.models

data class Main(
    val humidity: Double,
    val pressure: Double,
    val temperature: Double,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val feelsLike: Double
)
