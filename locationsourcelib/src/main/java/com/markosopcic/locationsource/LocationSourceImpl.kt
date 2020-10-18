package com.markosopcic.locationsource

import com.markosopcic.locationsource.model.Location
import com.markosopcic.locationsource.storage.dao.LocationDao
import com.markosopcic.locationsource.storage.model.DbLocation
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class LocationSourceImpl(private val locationDao: LocationDao) : LocationSource {

    override fun storeLocation(location: Location): Completable =
        locationDao.storeLocation(location.mapToDatabaseModel())

    override fun getStoredLocations(): Flowable<List<Location>> =
        locationDao.getStoredLocations().map {
            it.map(::mapToDomainModel)
        }

    override fun getSavedLocationForId(id: Int): Single<Location> = locationDao.getLocationById(id).map(::mapToDomainModel)


    private fun Location.mapToDatabaseModel() = DbLocation(id, name, longitude, latitude)

    private fun mapToDomainModel(dbLocation: DbLocation) = with(dbLocation) { Location(id!!, name, longitude, latitude) }

}
