package com.markosopcic.weatherdetails.ui

import com.markosopcic.weatherservicelib.models.Weather

sealed class WeatherDetailsViewState() {

    class YoutubeVideoId(val id: String?) : WeatherDetailsViewState()
    class LocationSaved(val saved: Boolean) : WeatherDetailsViewState()
    class Loading(val isLoading: Boolean) : WeatherDetailsViewState()
    class LocationForecast(
        val todayWeatherInfo: TodayWeatherInfo,
        val hourlyForecast: List<WeatherForecastItem>,
        val dailyForecast: List<WeatherForecastItem>
    ) : WeatherDetailsViewState()
}

class TodayWeatherInfo(
    val currentTemp: Int,
    val feelsLike: Int,
    val windSpeed: Int,
    val windDirection: Int,
    val humidity: Int,
    val pressure: Int,
    val weatherImageUrl: String,
    val sunrise: String,
    val sunset: String
)
