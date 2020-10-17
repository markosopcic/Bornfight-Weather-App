package com.markosopcic.locationsource

import com.markosopcic.locationsource.model.Location
import io.reactivex.Completable
import io.reactivex.Flowable

interface LocationSource {

    fun storeLocation(location: Location): Completable

    fun getStoredLocations(): Flowable<List<Location>>
}
