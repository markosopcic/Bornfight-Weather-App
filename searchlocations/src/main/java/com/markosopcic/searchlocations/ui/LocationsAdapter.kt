package com.markosopcic.searchlocations.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.markosopcic.searchlocations.R
import com.markosopcic.searchlocations.ui.LocationsAdapter.*
import kotlinx.android.synthetic.main.item_saved_location.view.*

class LocationsAdapter(private val context: Context) : RecyclerView.Adapter<LocationViewHolder>() {

    private var items: List<LocationItemViewState> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder =
        LocationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_saved_location, parent, false))

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) = holder.render(items[position])

    override fun getItemCount(): Int = items.size

    fun updateItems(items: List<LocationItemViewState>) {
        this.items = items
        notifyDataSetChanged()
    }

    class LocationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val nameText = view.location_name
        private val currentTemperature = view.location_temperature
        private val minTemperature = view.location_temperatureMin
        private val maxTemperature = view.location_temperatureMax
        private val weatherImage = view.location_weatherImage

        fun render(location: LocationItemViewState) {
            with(location) {
                val template = view.resources.getString(R.string.add_locations_celsius_format)
                Glide.with(view.context).load(location.iconUrl).into(weatherImage)
                nameText.text = name
                currentTemperature.text = template.format(temperature)
                maxTemperature.text = template.format(tempMax)
                minTemperature.text = template.format(tempMin)
            }
        }
    }
}
