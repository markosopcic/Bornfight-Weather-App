package com.markosopcic.weather

import android.app.Application
import com.markosopcic.searchlocations.di.searchLocationsModule
import com.markosopcic.core.di.baseModule
import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.locationsource.di.locationSourceModule
import com.markosopcic.permissionslib.di.permissionsModule
import com.markosopcic.userlocationsource.di.userLocationModule
import com.markosopcic.weather.di.appModule
import com.markosopcic.weatherdetails.di.weatherDetailsModule
import com.markosopcic.weatherservicelib.di.weatherServiceModule
import com.markosopcic.youtubevideosource.di.youtubeVideoSourceModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                appModule(),
                baseModule(),
                locationSourceModule(),
                permissionsModule(),
                searchLocationsModule(),
                userLocationModule(),
                weatherDetailsModule(),
                weatherServiceModule(),
                youtubeVideoSourceModule()
            )
        }

        get<RoutingActionSender>().sendRoutingAction(Router::showSearchLocationsScreen)

        if (BuildConfig.BUILD_TYPE == "debug") Timber.plant(Timber.DebugTree())
    }
}
