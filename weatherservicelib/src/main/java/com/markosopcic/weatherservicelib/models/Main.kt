package com.markosopcic.weatherservicelib.models

data class Main(
    val humidity: Int,
    val pressure: Int,
    val temperature: Double,
    val temperatureMax: Double,
    val temperatureMin: Double
)
