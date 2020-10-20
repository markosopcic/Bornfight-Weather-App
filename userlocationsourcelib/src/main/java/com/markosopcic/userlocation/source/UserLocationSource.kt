package com.markosopcic.userlocation.source

import com.markosopcic.locationsource.model.Location
import io.reactivex.Single

interface UserLocationSource {

    fun getUserLocation(): Single<Location>
}
