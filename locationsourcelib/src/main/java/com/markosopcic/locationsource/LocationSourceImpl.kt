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
            it.map { it.mapToDomainModel() }
        }

    override fun getSavedLocationForId(id: Int): Single<Location> = locationDao.getLocationById(id).map { it.mapToDomainModel() }

    override fun isLocationSaved(location: Location) = locationDao.findByNameAndCoordinates(location.latitude, location.longitude, location.name).map { it.any() }

    override fun deleteLocation(id: Int): Completable = locationDao.deleteLocation(id)

    private fun Location.mapToDatabaseModel() = DbLocation(id, name, longitude, latitude)

    private fun DbLocation.mapToDomainModel() = Location(id, name, longitude, latitude)

}
