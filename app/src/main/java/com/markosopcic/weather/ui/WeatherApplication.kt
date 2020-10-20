package com.markosopcic.weather.ui

import android.app.Application
import com.markosopcic.alertinglib.di.alertsModule
import com.markosopcic.searchlocations.di.searchLocationsModule
import com.markosopcic.core.di.baseModule
import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.locationsource.di.locationSourceModule
import com.markosopcic.networkinglib.di.CONNECTIVITY_BROADCAST_RECEIVER
import com.markosopcic.networkinglib.di.CONNECTIVITY_CHANGE_INTENT_FILTER
import com.markosopcic.networkinglib.di.networkingModule
import com.markosopcic.permissionslib.di.permissionsModule
import com.markosopcic.userlocation.di.userLocationModule
import com.markosopcic.weather.BuildConfig
import com.markosopcic.weather.di.appModule
import com.markosopcic.weatherdetails.di.weatherDetailsModule
import com.markosopcic.weatherservicelib.di.weatherServiceModule
import com.markosopcic.youtubevideo.di.youtubeVideoSourceModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import timber.log.Timber


class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WeatherApplication)
            modules(
                alertsModule(),
                appModule(),
                baseModule(),
                locationSourceModule(),
                networkingModule(),
                permissionsModule(),
                searchLocationsModule(),
                userLocationModule(),
                weatherDetailsModule(),
                weatherServiceModule(),
                youtubeVideoSourceModule()
            )
        }

        get<RoutingActionSender>().sendRoutingAction(Router::showSearchLocationsScreen)

        registerReceiver(get(named(CONNECTIVITY_BROADCAST_RECEIVER)), get(named(CONNECTIVITY_CHANGE_INTENT_FILTER)))

        if (BuildConfig.BUILD_TYPE == "debug") Timber.plant(Timber.DebugTree())
    }
}
