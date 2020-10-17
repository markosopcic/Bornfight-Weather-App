package com.markosopcic.locationsource.usecase

import com.markosopcic.core.usecase.QueryUseCase
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.model.Location
import io.reactivex.Flowable

class GetSavedLocations(private val locationSource: LocationSource) : QueryUseCase<List<Location>> {

    override fun invoke(): Flowable<List<Location>> = locationSource.getStoredLocations()
}
