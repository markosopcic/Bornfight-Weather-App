package com.markosopcic.userlocationsource.usecase

import com.markosopcic.core.usecase.QueryUseCase
import com.markosopcic.locationsource.model.Location
import com.markosopcic.userlocationsource.UserLocationSource
import io.reactivex.Flowable

class GetUserLocation(private val userLocationSource: UserLocationSource) : QueryUseCase<Location> {

    override fun invoke(): Flowable<Location> = userLocationSource.getUserLocation().toFlowable()
}
