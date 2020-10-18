package com.markosopcic.permissionslib.usecase

import com.markosopcic.core.usecase.CompletableUseCase
import com.markosopcic.permissionslib.permissionsource.PermissionSource
import io.reactivex.Completable

class RequestLocationPermission(private val permissionSource: PermissionSource) : CompletableUseCase {

    override fun invoke(): Completable = permissionSource.requestLocationPermission()
}
