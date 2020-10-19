package com.markosopcic.core.routing.config

import java.io.Serializable

data class WeatherDetailsConfig(val id: Int?, val name: String, val latitude: Double, val longitude: Double) : Serializable
