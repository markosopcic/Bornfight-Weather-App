package com.markosopcic.permissionslib.usecase

import com.markosopcic.core.routing.Router
import com.markosopcic.core.routing.RoutingActionSender
import com.markosopcic.core.usecase.CompletableUseCase
import io.reactivex.Completable

class RequireLocationPermissionAndEnabled
(
    private val isLocationEnabled: IsLocationEnabled,
    private val isLocationPermissionGranted: IsLocationPermissionGranted,
    private val requestLocationPermission: RequestLocationPermission,
    private val routingActionSender: RoutingActionSender
) : CompletableUseCase {

    override fun invoke(): Completable = isLocationPermissionGranted()
        .firstOrError()
        .flatMapCompletable { permissionGranted ->
            if (!permissionGranted) {
                requestLocationPermission()
            } else {
                isLocationEnabled().switchMapCompletable { enabled ->
                    if (!enabled) {
                        routingActionSender.sendRoutingAction(Router::showEnableLocationScreen)
                        Completable.error(IllegalAccessError("Location not enabled!"))
                    } else {
                        Completable.complete()
                    }
                }
            }
        }
}
