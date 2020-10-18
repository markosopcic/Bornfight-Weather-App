package com.markosopcic.weatherdetails.ui

import android.os.Bundle
import com.markosopcic.core.ui.BaseFragment
import com.markosopcic.core.ui.BaseViewModel
import com.markosopcic.locationweatherdetails.R
import kotlinx.android.synthetic.main.fragment_weather_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val LATITUDE_KEY = "LATITUDE"
private const val LONGITUDE_KEY = "LONGITUDE"
private const val ADDRESS_KEY = "ADDRESS"

class WeatherDetailsFragment : BaseFragment<WeatherDetailsViewState>(R.layout.fragment_weather_details) {

    companion object {
        fun getInstance(name: String, latitude: Double, longitude: Double) = WeatherDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ADDRESS_KEY, name)
                putDouble(LATITUDE_KEY, latitude)
                putDouble(LONGITUDE_KEY, longitude)
            }
        }
    }

    override fun initialiseView() {
        super.initialiseView()
        requireArguments().apply {
            name.text = getString(ADDRESS_KEY)
            longitude.text = getDouble(LONGITUDE_KEY).toString()
            latitude.text = getDouble(LATITUDE_KEY).toString()
        }
    }

    override val viewModel: WeatherDetailsViewModel by viewModel()

    override fun render(viewState: WeatherDetailsViewState) {
        TODO("Not yet implemented")
    }

}
