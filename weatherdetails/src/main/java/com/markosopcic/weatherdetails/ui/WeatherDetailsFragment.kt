package com.markosopcic.weatherdetails.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.markosopcic.core.routing.config.WeatherDetailsConfig
import com.markosopcic.core.ui.BaseFragment
import com.markosopcic.core.ui.setVisible
import com.markosopcic.locationsource.model.Location
import com.markosopcic.locationweatherdetails.R
import com.markosopcic.weatherdetails.ui.WeatherDetailsViewState.*
import kotlinx.android.synthetic.main.fragment_weather_details.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val CONFIG_KEY = "CONFIG"

class WeatherDetailsFragment : BaseFragment<WeatherDetailsViewState>(R.layout.fragment_weather_details) {

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

        weather_details_save_location.setOnClickListener { viewModel.toggleLocationSaved() }
    }

    override fun render(viewState: WeatherDetailsViewState) = when (viewState) {
        is LocationForecast -> {
            updateTodayWeatherData(viewState.todayWeatherInfo)
            hourlyAdapter.updateItems(viewState.hourlyForecast)
            dailyAdapter.updateItems(viewState.dailyForecast)
        }
        is Loading -> weather_details_spinner.setVisible(viewState.isLoading)
        is LocationSaved -> weather_details_save_location.setImageResource(if (viewState.saved) R.drawable.ic_baseline_delete_24 else R.drawable.ic_baseline_save_24)
    }


    private fun updateTodayWeatherData(viewState: TodayWeatherInfo) {
        with(viewState) {
            Glide.with(requireContext()).load(weatherImageUrl).into(weather_details_currentWeatherImage)
            weather_details_currentTemperatureAndFeelsLike.text = getString(R.string.weather_details_current_temperature_format, currentTemp, feelsLike)
            weather_details_windSpeedNow.text = getString(R.string.weather_details_wind_speed_format, windSpeed)
            weather_details_windDirectionNow.text = getString(R.string.weather_details_wind_direction_format, windDirection)
            weather_details_humidity.text = getString(R.string.weather_details_percentage_format, humidity)
            weather_details_pressure.text = getString(R.string.weather_details_pressure_format, pressure)
            weather_details_sunset.text = sunset
            weather_details_sunrise.text = sunrise
        }
    }

}
