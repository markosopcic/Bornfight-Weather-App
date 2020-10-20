package com.markosopcic.alertinglib.alertsource

import io.reactivex.Completable
import io.reactivex.Flowable

interface AlertSource {

    fun getAlerts(): Flowable<String>

    fun publishAlert(message: String): Completable
}
