package com.markosopcic.locationsource.usecase

import com.markosopcic.core.usecase.QueryUseCaseWithParam
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.model.Location
import io.reactivex.Flowable

class IsLocationSaved(private val locationSource: LocationSource) : QueryUseCaseWithParam<Location, Boolean> {

    override fun invoke(param: Location): Flowable<Boolean> = locationSource.isLocationSaved(param)
}
