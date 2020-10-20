package com.markosopcic.networkinglib.di

import android.content.BroadcastReceiver
import android.content.IntentFilter
import com.markosopcic.core.di.BACKGROUND_SCHEDULER
import com.markosopcic.networkinglib.networkavailabilitysource.NetworkAvailabilitySource
import com.markosopcic.networkinglib.networkavailabilitysource.NetworkAvailabilitySourceImpl
import com.markosopcic.networkinglib.usecase.IsNetworkAvailable
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val CONNECTIVITY_CHANGE_INTENT_FILTER = "CONNECTIVITY_CHANGE_INTENT_FILTER"
const val CONNECTIVITY_BROADCAST_RECEIVER = "CONNECTIVITY_BROADCAST_RECEIVER"
const val CONNECTIVITY_CHANGE_INTENT = "android.net.conn.CONNECTIVITY_CHANGE"

fun networkingModule() = module {
    single<NetworkAvailabilitySource> { get<NetworkAvailabilitySourceImpl>() }

    single { NetworkAvailabilitySourceImpl(get(named(BACKGROUND_SCHEDULER))) }

    single { IsNetworkAvailable(get()) }

    single<BroadcastReceiver>(named(CONNECTIVITY_BROADCAST_RECEIVER)) {
        get<NetworkAvailabilitySourceImpl>()
    }

    single(named(CONNECTIVITY_CHANGE_INTENT_FILTER)) {
        IntentFilter().apply {
            addAction(CONNECTIVITY_CHANGE_INTENT)
        }
    }
}
