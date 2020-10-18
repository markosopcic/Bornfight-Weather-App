package com.markosopcic.locationsource.usecase

import com.markosopcic.core.usecase.QueryUseCaseWithParam
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.model.Location
import io.reactivex.Flowable

class GetSavedLocationById(private val locationSource: LocationSource) : QueryUseCaseWithParam<Int, Location> {

    override fun invoke(param: Int): Flowable<Location> = locationSource.getSavedLocationForId(param).toFlowable()
}
