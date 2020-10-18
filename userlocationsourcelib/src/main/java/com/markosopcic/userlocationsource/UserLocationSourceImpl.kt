package com.markosopcic.userlocationsource

import android.Manifest
import android.app.Service
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.markosopcic.locationsource.model.Location
import io.reactivex.Single

class UserLocationSourceImpl(private val context: Context, private val geocoder: Geocoder) : UserLocationSource {

    private val placesFields = listOf(Place.Field.ID, Place.Field.LAT_LNG)

    override fun getUserLocation(): Single<Location> = Single.create<Location> { emitter ->
        if (!checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            emitter.onError(IllegalAccessException("You don't have location permissions"))
            return@create
        }

        Places.createClient(context).findCurrentPlace(FindCurrentPlaceRequest.newInstance(placesFields)).addOnSuccessListener {
            val place = it.placeLikelihoods.sortedByDescending { it.likelihood }.first()
            val location = with(place.place) {
                val geoLocation = geocoder.getFromLocation(latLng!!.latitude, latLng!!.longitude, 5).first()
                Location(-1, "${geoLocation.locality}, ${geoLocation.countryName}", geoLocation.longitude, geoLocation.latitude)
            }
            emitter.onSuccess(location)
        }.addOnFailureListener {
            emitter.onError(it)
        }
    }

    private fun checkPermission(permission: String) =
        ActivityCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED
}
