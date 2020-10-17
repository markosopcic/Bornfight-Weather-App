package com.markosopcic.weatherservicelib.models

import com.markosopcic.weatherservicelib.apimodels.*

data class WeatherStatus(
    val base: String,
    val clouds: Int,
    val coordinates: Coordinates,
    val timestamp: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val general: General,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)















