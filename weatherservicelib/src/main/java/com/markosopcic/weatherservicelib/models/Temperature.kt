package com.markosopcic.weatherservicelib.models

data class Temperature(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morning: Double,
    val night: Double
)
