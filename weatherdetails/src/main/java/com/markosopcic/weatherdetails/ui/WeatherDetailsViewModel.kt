package com.markosopcic.weatherdetails.ui

import com.markosopcic.core.publishProgress
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.ui.BaseViewModel
import com.markosopcic.locationsource.model.Location
import com.markosopcic.locationsource.usecase.GetSavedLocations
import com.markosopcic.locationsource.usecase.RemoveSavedLocation
import com.markosopcic.locationsource.usecase.SaveLocation
import com.markosopcic.weatherdetails.ui.WeatherDetailsViewState.Loading
import com.markosopcic.weatherdetails.ui.WeatherDetailsViewState.LocationSaved
import com.markosopcic.weatherservicelib.models.DetailedWeatherStatus
import com.markosopcic.weatherservicelib.usecase.GetDetailedWeatherForLocation
import com.markosopcic.youtubevideosource.usecase.SearchYoutubeVideos
import io.reactivex.Scheduler
import io.reactivex.processors.BehaviorProcessor
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

private const val SHORT_DAY_FORMAT = "EEE"
private const val HOUR_FORMAT = "hh:mm"

class WeatherDetailsViewModel(
    private val location: Location,
    private val saveLocation: SaveLocation,
    private val getSavedLocations: GetSavedLocations,
    private val removeSavedLocation: RemoveSavedLocation,
    private val searchYoutubeVideos: SearchYoutubeVideos,
    getDetailedWeatherForLocation: GetDetailedWeatherForLocation,
    backgroundScheduler: Scheduler,
    mainScheduler: Scheduler,
    routingActionSender: RoutingActionSender
) : BaseViewModel<WeatherDetailsViewState>(backgroundScheduler, mainScheduler, routingActionSender) {

    private val progressProcessor = BehaviorProcessor.create<Boolean>()

    private val videoQueryProcessor = BehaviorProcessor.create<String>()

    init {
        observe(progressProcessor.map(::Loading))

        observe(videoQueryProcessor.switchMap { searchYoutubeVideos(it) }.map { WeatherDetailsViewState.YoutubeVideoId(it.firstOrNull()?.id) })

        observe(getSavedLocations().map { LocationSaved(it.any { it.name == location.name && it.latitude == location.latitude && it.longitude == location.longitude }) })

        observe(getDetailedWeatherForLocation(location).doOnNext {

        }.map {
            val query = "${location.name}, ${it.current.weather.firstOrNull()?.main ?: ""}"
            videoQueryProcessor.onNext(query)
            WeatherDetailsViewState.LocationForecast(
                it.getCurrentWeather(),
                it.getHourlyForecast(),
                it.getDailyForecast()
            )

        }.publishProgress(progressProcessor))
    }

    fun toggleLocationSaved() = execute(getSavedLocations().map {
        it.firstOrNull { it.name == location.name && it.latitude == location.latitude && it.longitude == location.longitude } ?: Location.EMPTY
    }
        .firstOrError()
        .flatMapCompletable {
            if (it != Location.EMPTY) {
                removeSavedLocation(it.id!!)
            } else {
                saveLocation(location)
            }
        })

    private fun DetailedWeatherStatus.getDailyForecast() = this.daily.map {
        WeatherForecastItem(
            it.timestampSeconds.getDayFromUnixSeconds(),
            it.temp.max.toInt(),
            it.precipitationPercentage,
            it.weather.firstOrNull()?.icon ?: ""
        )
    }

    private fun DetailedWeatherStatus.getHourlyForecast() = this.hourly.map {
        WeatherForecastItem(
            it.timestampSeconds.getHourDayFromUnixSeconds(),
            it.temperature.toInt(),
            it.precipitationPercentage,
            it.weather.firstOrNull()?.icon ?: ""
        )
    }

    private fun DetailedWeatherStatus.getCurrentWeather() = with(this.current) {
        TodayWeatherInfo(
            temp.toInt(),
            feelsLike.toInt(),
            windSpeed.toInt(),
            windDeg,
            humidity,
            pressure,
            weather.firstOrNull()?.icon ?: "",
            sunrise.getTimeFormatFromUnixSeconds(),
            sunset.getTimeFormatFromUnixSeconds()
        )
    }

    private fun Int.getDayFromUnixSeconds(): String {
        val date = Date(TimeUnit.SECONDS.toMillis(this.toLong()))
        val format = SimpleDateFormat(SHORT_DAY_FORMAT, Locale.getDefault())
        return format.format(date)
    }

    private fun Int.getHourDayFromUnixSeconds(): String {
        val date = Date(TimeUnit.SECONDS.toMillis(this.toLong()))
        val format = SimpleDateFormat("$HOUR_FORMAT, $SHORT_DAY_FORMAT", Locale.getDefault())
        return format.format(date)
    }

    private fun Int.getTimeFormatFromUnixSeconds(): String {
        val date = Date(TimeUnit.SECONDS.toMillis(this.toLong()))
        val format = SimpleDateFormat.getTimeInstance()
        return format.format(date)
    }
}
