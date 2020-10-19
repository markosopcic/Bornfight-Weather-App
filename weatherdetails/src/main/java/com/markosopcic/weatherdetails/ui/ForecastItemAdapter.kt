package com.markosopcic.weatherdetails.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.markosopcic.locationweatherdetails.R
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.koin.core.parameter.parametersOf

class ForecastItemAdapter(private val context: Context) : RecyclerView.Adapter<ForecastItemAdapter.ForecastViewHolder>() {

    private var items: List<WeatherForecastItem> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(LayoutInflater.from(context).inflate(R.layout.item_forecast, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) = holder.render(items[position])

    override fun getItemCount(): Int = items.size

    fun updateItems(items: List<WeatherForecastItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ForecastViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val timeText = view.forecast_item_time
        private val imageText = view.forecast_item_weatherImage
        private val precipitationText = view.forecast_item_precipitation
        private val temperatureText = view.forecast_item_temperature

        fun render(forecastItem: WeatherForecastItem) {
            with(forecastItem) {
                timeText.text = forecastTime
                Glide.with(view).load(weatherImageUrl).into(imageText)
                precipitationText.text = view.context.getString(R.string.weather_details_percentage_format, precipitation)
                temperatureText.text = view.context.getString(R.string.weather_details_temperature_celsius_format, temperature)
            }
        }
    }

}
