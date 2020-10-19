package com.markosopcic.locationsource.di

import androidx.room.Room
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.LocationSourceImpl
import com.markosopcic.locationsource.storage.database.LocationDatabase
import com.markosopcic.locationsource.usecase.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "LOCATION_DATABASE"

fun locationSourceModule() = module {

    single {
        Room.databaseBuilder(androidContext(), LocationDatabase::class.java, DATABASE_NAME).build()
    }

    single<LocationSource> { LocationSourceImpl(get<LocationDatabase>().locationDao) }

    single { GetSavedLocations(get()) }

    single { SaveLocation(get()) }

    single { GetSavedLocationById(get()) }

    single { IsLocationSaved(get()) }

    single { RemoveSavedLocation(get()) }
}
