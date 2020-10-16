package com.markosopcic.weather

import android.app.Application
import com.markosopcic.core.di.coreModule
import com.markosopcic.weather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                coreModule(),
                appModule()
            )
        }

        if (BuildConfig.BUILD_TYPE == "debug") Timber.plant(Timber.DebugTree())
    }
}
