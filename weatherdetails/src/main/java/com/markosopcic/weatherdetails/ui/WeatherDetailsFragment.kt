package com.markosopcic.weatherdetails.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import com.markosopcic.core.routing.config.WeatherDetailsConfig
import com.markosopcic.core.ui.BaseFragment
import com.markosopcic.core.ui.setVisible
import com.markosopcic.locationsource.model.Location
import com.markosopcic.locationweatherdetails.R
import com.markosopcic.weatherdetails.ui.WeatherDetailsViewState.*
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.fragment_weather_details.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import java.util.concurrent.TimeUnit

private const val CONFIG_KEY = "CONFIG"

class WeatherDetailsFragment : BaseFragment<WeatherDetailsViewState>(R.layout.fragment_weather_details) {

    private lateinit var youTubePlayer: YouTubePlayer

    companion object {
        fun getInstance(config: WeatherDetailsConfig) = WeatherDetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(CONFIG_KEY, config)
            }
        }
    }

    override val viewModel: WeatherDetailsViewModel by viewModel {
        (requireArguments().getSerializable(CONFIG_KEY)!! as WeatherDetailsConfig).run {
            parametersOf(Location(id, name, longitude, latitude))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = YouTubePlayerSupportFragmentX.newInstance()
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.weatherDetails_youtubePlaceholder, fragment).commit()
        fragment.initialize(getString(R.string.base_youtubeApiKey), object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider, player: YouTubePlayer, p2: Boolean) {
                youTubePlayer = player
                player.setOnFullscreenListener {
                    requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
                }
            }

            override fun onInitializationFailure(provider: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                Timber.d(p1?.name)
            }

        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private val hourlyAdapter: ForecastItemAdapter by inject { parametersOf(requireContext()) }

    private val dailyAdapter: ForecastItemAdapter by inject { parametersOf(requireContext()) }

    override fun initialiseView() {
        super.initialiseView()
        requireArguments().apply {
            weather_details_locationName.text = (getSerializable(CONFIG_KEY) as WeatherDetailsConfig).name
        }

        weather_details_hourlyRecyclerView.apply {
            adapter = hourlyAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        weather_details_dailyRecyclerView.apply {
            adapter = dailyAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        weather_details_saveLocation.setOnClickListener { viewModel.toggleLocationSaved() }
    }

    override fun render(viewState: WeatherDetailsViewState) = when (viewState) {
        is LocationForecast -> {
            updateTodayWeatherData(viewState.todayWeatherInfo)
            hourlyAdapter.updateItems(viewState.hourlyForecast)
            dailyAdapter.updateItems(viewState.dailyForecast)
        }
        is Loading -> weather_details_spinner.setVisible(viewState.isLoading)
        is LocationSaved -> weather_details_saveLocation.setImageResource(if (viewState.saved) R.drawable.ic_baseline_delete_24 else R.drawable.ic_baseline_save_24)
        is YoutubeVideoId -> updateYoutubeVideo(viewState.id)
    }

    private fun updateYoutubeVideo(id: String?) {
        weatherDetails_youtubePlaceholder.setVisible(id != null)
        if (id != null) {
            addDisposable(Flowable.interval(500, TimeUnit.MILLISECONDS).takeUntil {
                ::youTubePlayer.isInitialized
            }.doOnComplete { youTubePlayer.cueVideo(id) }.subscribe())
        }
    }


    private fun updateTodayWeatherData(viewState: TodayWeatherInfo) {
        with(viewState) {
            Glide.with(requireContext()).load(weatherImageUrl).into(weather_details_currentWeatherImage)
            weather_details_currentWeatherAndFeelsLike.text =
                getString(R.string.weather_details_current_temperature_format, weather, currentTemp, feelsLike)
            weather_details_windSpeedNow.text = getString(R.string.weather_details_wind_speed_format, windSpeed)
            weather_details_windDirectionNow.text = getString(R.string.weather_details_wind_direction_format, windDirection)
            weather_details_humidity.text = getString(R.string.weather_details_percentage_format, humidity)
            weather_details_pressure.text = getString(R.string.weather_details_pressure_format, pressure)
            weather_details_tempHigh.text = getString(R.string.weather_details_temperature_celsius_format, maxTemp)
            weather_details_tempLow.text = getString(R.string.weather_details_temperature_celsius_format, minTemp)
            weather_details_sunset.text = sunset
            weather_details_sunrise.text = sunrise
        }
    }

}
