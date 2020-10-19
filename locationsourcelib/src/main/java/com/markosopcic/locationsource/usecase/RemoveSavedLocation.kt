package com.markosopcic.locationsource.usecase

import com.markosopcic.core.usecase.CompletableUseCaseWithParam
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.model.Location
import io.reactivex.Completable

class RemoveSavedLocation(private val locationSource: LocationSource) : CompletableUseCaseWithParam<Int> {

    override fun invoke(param: Int): Completable = locationSource.deleteLocation(param)
}
