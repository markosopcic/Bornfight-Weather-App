package com.markosopcic.alertinglib.usecase

import com.markosopcic.alertinglib.alertsource.AlertSource
import com.markosopcic.core.usecase.CompletableUseCaseWithParam
import io.reactivex.Completable

class PublishAlert(private val alertSource: AlertSource) : CompletableUseCaseWithParam<String> {

    override fun invoke(param: String): Completable = alertSource.publishAlert(param)
}
