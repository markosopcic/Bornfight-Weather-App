package com.markosopcic.locationsource.di

import android.location.Geocoder
import androidx.room.Room
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.LocationSourceImpl
import com.markosopcic.locationsource.storage.database.LocationDatabase
import com.markosopcic.locationsource.usecase.GetSavedLocations
import com.markosopcic.locationsource.usecase.SaveLocation
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "LOCATION_DATABASE"

fun locationSourceModule() = module {

    single {
        val context = androidContext()
        //Preferred way of acquiring locale is configuration.locales.get(0) but it requires API level 24 which is why we're using deprecated locale.
        Geocoder(context, context.resources.configuration.locale)
    }

    single { Room.databaseBuilder(androidContext(), LocationDatabase::class.java, DATABASE_NAME).build() }

    single<LocationSource> { LocationSourceImpl(get<LocationDatabase>().locationDao) }

    single { GetSavedLocations(get()) }

    single { SaveLocation(get()) }

}
