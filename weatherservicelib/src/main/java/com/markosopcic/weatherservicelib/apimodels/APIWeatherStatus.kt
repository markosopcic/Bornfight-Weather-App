package com.markosopcic.weatherservicelib.apimodels

import com.squareup.moshi.Json

data class APIWeatherStatus(
    @field:Json(name = "base")
    val base: String,
    @field:Json(name = "clouds")
    val clouds: APIClouds,
    @field:Json(name = "coord")
    val coordinates: APICoordinates,
    @field:Json(name = "dt")
    val timestamp: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: APIMain,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "sys")
    val general: APIGeneral,
    @field:Json(name = "visibility")
    val visibility: Int,
    @field:Json(name = "weather")
    val weather: List<APIWeather>,
    @field:Json(name = "wind")
    val wind: APIWind
)


data class APIClouds(
    @field:Json(name = "all")
    val all: Int
)

data class APICoordinates(
    @field:Json(name = "lat")
    val latitude: Double,
    @field:Json(name = "lon")
    val longitude: Double
)

data class APIMain(
    @field:Json(name = "humidity")
    val humidity: Int,
    @field:Json(name = "pressure")
    val pressure: Int,
    @field:Json(name = "temp")
    val temperature: Double,
    @field:Json(name = "temp_max")
    val temperatureMax: Double,
    @field:Json(name = "temp_min")
    val temperatureMin: Double,
    @field:Json(name = "feels_like")
    val feelsLikeTemp: Double
)

data class APIGeneral(
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "sunrise")
    val sunrise: Int,
    @field:Json(name = "sunset")
    val sunset: Int,
    @field:Json(name = "type")
    val type: Int
)


data class APIWeather(
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "icon")
    val icon: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "main")
    val main: String
)


data class APIWind(
    @field:Json(name = "deg")
    val degree: Int,
    @field:Json(name = "speed")
    val speed: Double
)
