package com.markosopcic.alertinglib.usecase

import com.markosopcic.alertinglib.alertsource.AlertSource
import com.markosopcic.core.usecase.QueryUseCase
import io.reactivex.Flowable

class GetAlerts(private val alertSource: AlertSource) : QueryUseCase<String> {

    override fun invoke(): Flowable<String> = alertSource.getAlerts()
}
