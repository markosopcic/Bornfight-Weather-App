package com.markosopcic.weather.backgroundworkers

import android.content.Context
import com.markosopcic.alertinglib.usecase.PublishAlert
import com.markosopcic.networkinglib.usecase.IsNetworkAvailable
import com.markosopcic.weather.R
import io.reactivex.Scheduler

class NetworkWatcher(
    private val context: Context,
    private val backgroundScheduler: Scheduler,
    private val isNetworkAvailable: IsNetworkAvailable,
    private val publishAlert: PublishAlert
) : BaseWorker() {

    override fun startWork() {
        addDisposable(
            isNetworkAvailable()
                .switchMapCompletable {
                    publishAlert(context.getString(if (it) R.string.network_robot_network_restored else R.string.network_robot_network_lost))
                }.subscribeOn(backgroundScheduler)
                .subscribe()
        )
    }

    override fun stopWork() {
        disposables.clear()
    }

}
