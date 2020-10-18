package com.markosopcic.permissionslib.permissionsource

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.markosopcic.core.routing.RoutingActionSender
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class PermissionSourceImpl(private val context: Context, private val routingActionSender: RoutingActionSender) : PermissionSource, ActivityCompat.OnRequestPermissionsResultCallback {

    private val locationPermissionEnabled: BehaviorProcessor<Boolean> = BehaviorProcessor.createDefault(checkPermission(ACCESS_FINE_LOCATION))

    override fun requestLocationPermission(): Completable = Completable.fromAction {
        routingActionSender.sendRoutingAction { it.requestPermissions(arrayOf(ACCESS_FINE_LOCATION)) }
    }

    override fun locationPermissionGranted(): Flowable<Boolean> = locationPermissionEnabled.distinctUntilChanged()

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (ACCESS_FINE_LOCATION in permissions) {
            val isLocationGranted = grantResults[permissions.indexOf(ACCESS_FINE_LOCATION)] == PERMISSION_GRANTED
            locationPermissionEnabled.onNext(isLocationGranted)
        }
    }

    private fun checkPermission(permission: String) = ActivityCompat.checkSelfPermission(context, permission) == PERMISSION_GRANTED
}
