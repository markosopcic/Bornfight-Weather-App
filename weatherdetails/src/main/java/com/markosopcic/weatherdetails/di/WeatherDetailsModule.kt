package com.markosopcic.weatherdetails.di

import com.markosopcic.core.di.BACKGROUND_SCHEDULER
import com.markosopcic.core.di.MAIN_SCHEDULER
import com.markosopcic.weatherdetails.ui.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun weatherDetailsModule() = module {

    viewModel { WeatherDetailsViewModel(get(named(BACKGROUND_SCHEDULER)), get(named(MAIN_SCHEDULER)), get()) }
}
