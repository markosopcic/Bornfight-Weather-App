package com.markosopcic.locationsource.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.markosopcic.locationsource.storage.dao.LocationDao
import com.markosopcic.locationsource.storage.model.DbLocation

@Database(version = 1, entities = [DbLocation::class])
abstract class LocationDatabase : RoomDatabase() {

    abstract val locationDao: LocationDao
}
