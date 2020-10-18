package com.markosopcic.permissionslib.permissionsource

import io.reactivex.Completable
import io.reactivex.Flowable

interface PermissionSource {

    fun requestLocationPermission(): Completable

    fun locationPermissionGranted(): Flowable<Boolean>
}
