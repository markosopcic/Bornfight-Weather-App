package com.markosopcic.permissionslib.usecase

import com.markosopcic.core.usecase.QueryUseCase
import com.markosopcic.permissionslib.permissionsource.PermissionSource
import io.reactivex.Flowable

class IsLocationEnabled(private val permissionSource: PermissionSource) : QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = permissionSource.isLocationEnabled().toFlowable()
}
