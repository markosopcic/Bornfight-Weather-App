package com.markosopcic.weather

import android.app.Application
import com.markosopcic.searchlocations.di.searchLocationsModule
import com.markosopcic.core.di.baseModule
import com.markosopcic.locationsource.di.locationSourceModule
import com.markosopcic.weather.di.appModule
import com.markosopcic.weatherservicelib.di.weatherServiceModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                searchLocationsModule(),
                appModule(),
                baseModule(),
                locationSourceModule(),
                weatherServiceModule()
            )
        }

        if (BuildConfig.BUILD_TYPE == "debug") Timber.plant(Timber.DebugTree())
    }
}
