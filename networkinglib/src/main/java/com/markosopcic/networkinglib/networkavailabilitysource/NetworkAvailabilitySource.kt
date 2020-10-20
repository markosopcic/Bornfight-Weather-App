package com.markosopcic.networkinglib.networkavailabilitysource

import io.reactivex.Flowable

interface NetworkAvailabilitySource {

    fun isNetworkAvailable(): Flowable<Boolean>
}
