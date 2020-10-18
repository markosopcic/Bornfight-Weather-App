package com.markosopcic.userlocationsource

import com.markosopcic.locationsource.model.Location
import io.reactivex.Single

interface UserLocationSource {

    fun getUserLocation(): Single<Location>
}
