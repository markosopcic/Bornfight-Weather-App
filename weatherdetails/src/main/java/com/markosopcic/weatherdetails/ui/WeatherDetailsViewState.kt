package com.markosopcic.weatherdetails.ui

sealed class WeatherDetailsViewState() {

    class LocationSaved(val saved: Boolean) : WeatherDetailsViewState()
    class Loading(val isLoading: Boolean) : WeatherDetailsViewState()
    class LocationForecast(val todayWeatherInfo: TodayWeatherInfo, val hourlyForecast: List<WeatherForecastItem>, val dailyForecast: List<WeatherForecastItem>) : WeatherDetailsViewState()
}

class TodayWeatherInfo(val currentTemp: Int,
                       val feelsLike: Int,
                       val windSpeed: Int,
                       val windDirection: Int,
                       val humidity: Int,
                       val pressure: Int,
                       val weatherImageUrl: String,
                       val sunrise: String,
                       val sunset: String
)
