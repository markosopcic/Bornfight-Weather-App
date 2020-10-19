package com.markosopcic.weatherservicelib.models


data class DetailedWeatherStatus(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Double,
    val lon: Double,
    val minutely: List<Minutely>?,
    val timezone: String,
    val timezoneOffset: Int
)














