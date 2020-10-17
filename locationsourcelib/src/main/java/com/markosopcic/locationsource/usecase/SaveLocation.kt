package com.markosopcic.locationsource.usecase

import com.markosopcic.core.usecase.CompletableUseCase
import com.markosopcic.core.usecase.CompletableUseCaseWithParam
import com.markosopcic.locationsource.LocationSource
import com.markosopcic.locationsource.model.Location
import io.reactivex.Completable

class SaveLocation(private val locationSource: LocationSource) : CompletableUseCaseWithParam<Location> {

    override fun invoke(param: Location): Completable = locationSource.storeLocation(param)
}
