package com.markosopcic.weatherservicelib.models

data class General(
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)
