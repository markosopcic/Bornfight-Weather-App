package com.markosopcic.weather.di

import androidx.appcompat.app.AppCompatActivity
import com.markosopcic.core.di.BACKGROUND_SCHEDULER
import com.markosopcic.core.routing.Router
import com.markosopcic.weather.backgroundworkers.BaseWorker
import com.markosopcic.weather.backgroundworkers.NetworkWatcher
import com.markosopcic.weather.routing.RouterImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val WORKERS = "WORKERS"

fun appModule() = module {

    factory<Router> {
        val activity: AppCompatActivity = it[0]
        RouterImpl(activity, activity.supportFragmentManager)
    }

    single<List<BaseWorker>>(named(WORKERS)) {
        listOf(NetworkWatcher(get(), get(named(BACKGROUND_SCHEDULER)), get(), get()))
    }
}
