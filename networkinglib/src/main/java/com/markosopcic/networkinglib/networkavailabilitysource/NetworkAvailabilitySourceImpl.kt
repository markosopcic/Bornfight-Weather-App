package com.markosopcic.networkinglib.networkavailabilitysource

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.markosopcic.networkinglib.di.CONNECTIVITY_CHANGE_INTENT
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.processors.PublishProcessor

class NetworkAvailabilitySourceImpl(private val backgroundScheduler: Scheduler) : NetworkAvailabilitySource, BroadcastReceiver() {

    private val networkAvailableProcessor = PublishProcessor.create<Boolean>()

    private var initialized: Boolean = false

    private val networkAvailabilityStatus = networkAvailableProcessor.distinctUntilChanged().subscribeOn(backgroundScheduler)

    override fun isNetworkAvailable(): Flowable<Boolean> = networkAvailabilityStatus

    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action != CONNECTIVITY_CHANGE_INTENT) return

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //using deprecated because of API level 21
        val connectionAvailable = connectivityManager.allNetworkInfo.any { it.isConnected }

        //So we don't get updates right away at starting the app
        if (!initialized && connectionAvailable) {
            initialized = true
            return
        } else if (!initialized) initialized = true
        networkAvailableProcessor.onNext(connectionAvailable)
    }

}
