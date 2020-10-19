package com.markosopcic.permissionslib.permissionsource

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PermissionSource {

    fun requestLocationPermission(): Completable

    fun locationPermissionGranted(): Flowable<Boolean>

    fun isLocationEnabled(): Single<Boolean>
}
