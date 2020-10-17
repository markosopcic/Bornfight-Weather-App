package com.markosopcic.locationsource.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.markosopcic.locationsource.storage.model.DbLocation
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface LocationDao {

    @Query("select * from DbLocation")
    fun getStoredLocations(): Flowable<List<DbLocation>>

    @Insert
    fun storeLocation(location: DbLocation): Completable
}
