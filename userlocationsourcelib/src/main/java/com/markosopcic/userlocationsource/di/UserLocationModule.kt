package com.markosopcic.userlocationsource.di

import android.location.Geocoder
import com.markosopcic.userlocationsource.UserLocationSource
import com.markosopcic.userlocationsource.UserLocationSourceImpl
import com.markosopcic.userlocationsource.usecase.GetUserLocation
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun userLocationModule() = module {

    single<UserLocationSource> { UserLocationSourceImpl(get(), get()) }

    single { GetUserLocation(get()) }

    /*we have to use deprecated locale because of api level 21 */
    single { Geocoder(get(), androidContext().resources.configuration.locale) }
}
