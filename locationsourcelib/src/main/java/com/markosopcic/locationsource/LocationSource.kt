package com.markosopcic.locationsource

import com.markosopcic.locationsource.model.Location
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LocationSource {

    fun storeLocation(location: Location): Completable

    fun getStoredLocations(): Flowable<List<Location>>

    fun getSavedLocationForId(id: Int): Single<Location>
}
