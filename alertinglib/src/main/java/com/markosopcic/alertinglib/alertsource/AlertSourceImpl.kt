package com.markosopcic.alertinglib.alertsource

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

class AlertSourceImpl : AlertSource {

    private val alertProcessor = PublishProcessor.create<String>()

    override fun getAlerts(): Flowable<String> = alertProcessor

    override fun publishAlert(message: String): Completable = Completable.fromAction { alertProcessor.onNext(message) }

}
