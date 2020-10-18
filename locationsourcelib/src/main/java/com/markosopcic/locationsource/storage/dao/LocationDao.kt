package com.markosopcic.locationsource.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.markosopcic.locationsource.model.Location
import com.markosopcic.locationsource.storage.model.DbLocation
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface LocationDao {

    @Query("select * from DbLocation")
    fun getStoredLocations(): Flowable<List<DbLocation>>

    @Insert
    fun storeLocation(location: DbLocation): Completable

    @Query("select * from DbLocation where id = :id")
    fun getLocationById(id: Int): Single<DbLocation>
}
