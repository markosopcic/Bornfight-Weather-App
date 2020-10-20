package com.markosopcic.networkinglib.usecase

import com.markosopcic.core.usecase.QueryUseCase
import com.markosopcic.networkinglib.networkavailabilitysource.NetworkAvailabilitySource
import io.reactivex.Flowable

class IsNetworkAvailable(private val networkAvailabilitySource: NetworkAvailabilitySource) : QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = networkAvailabilitySource.isNetworkAvailable()
}
