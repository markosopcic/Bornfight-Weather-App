package com.markosopcic.userlocation.di

import android.location.Geocoder
import com.markosopcic.userlocation.source.UserLocationSource
import com.markosopcic.userlocation.source.UserLocationSourceImpl
import com.markosopcic.userlocation.usecase.GetUserLocation
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun userLocationModule() = module {

    single<UserLocationSource> { UserLocationSourceImpl(get(), get()) }

    single { GetUserLocation(get()) }

    /*we have to use deprecated locale because of api level 21 */
    single { Geocoder(get(), androidContext().resources.configuration.locale) }
}
