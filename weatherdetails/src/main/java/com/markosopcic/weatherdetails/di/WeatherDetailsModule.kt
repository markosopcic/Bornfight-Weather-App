package com.markosopcic.weatherdetails.di

import android.content.Context
import com.markosopcic.core.di.BACKGROUND_SCHEDULER
import com.markosopcic.core.di.MAIN_SCHEDULER
import com.markosopcic.locationsource.model.Location
import com.markosopcic.weatherdetails.ui.ForecastItemAdapter
import com.markosopcic.weatherdetails.ui.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun weatherDetailsModule() = module {

    viewModel {
        val location: Location = it[0]
        WeatherDetailsViewModel(location, get(), get(), get(), get(), get(), get(named(BACKGROUND_SCHEDULER)), get(named(MAIN_SCHEDULER)), get())
    }

    factory {
        val context: Context = it[0]
        ForecastItemAdapter(context)
    }
}
