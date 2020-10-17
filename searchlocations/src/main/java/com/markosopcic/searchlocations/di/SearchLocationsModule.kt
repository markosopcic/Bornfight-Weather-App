package com.markosopcic.searchlocations.di

import android.content.Context
import com.markosopcic.searchlocations.ui.SearchLocationsViewModel
import com.markosopcic.searchlocations.ui.LocationsAdapter
import com.markosopcic.core.di.BACKGROUND_SCHEDULER
import com.markosopcic.core.di.MAIN_SCHEDULER
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun searchLocationsModule() = module {

    viewModel {
        SearchLocationsViewModel(get(), get(), get(named(BACKGROUND_SCHEDULER)), get(named(MAIN_SCHEDULER)), get())
    }

    factory {
        val context: Context = it[0]
        LocationsAdapter(context)
    }

}
