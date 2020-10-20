package com.markosopcic.alertinglib.di

import com.markosopcic.alertinglib.alertsource.AlertSource
import com.markosopcic.alertinglib.alertsource.AlertSourceImpl
import com.markosopcic.alertinglib.usecase.GetAlerts
import com.markosopcic.alertinglib.usecase.PublishAlert
import org.koin.dsl.module

fun alertsModule() = module {

    single<AlertSource> { AlertSourceImpl() }

    single { PublishAlert(get()) }

    single { GetAlerts(get()) }
}
