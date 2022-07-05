package ru.neoslax.weather.data.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.neoslax.weather.domain.repository.LocationTracker
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject constructor(
    private val application: Application,
    private val locationClient: FusedLocationProviderClient
) : LocationTracker {

    override suspend fun gerCurrentLocation(): Location? {
        if (!(checkLocationPermission() &&
                    checkGpsEnabled())
        ) return null

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(value = result)
                    } else {
                        cont.resume(value = null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
                addOnFailureListener {
                    cont.resume(null)
                }
            }
        }
    }

    private fun checkLocationPermission(): Boolean {
        val hasAccessFineLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocation = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        return hasAccessCoarseLocation && hasAccessFineLocation
    }

    private fun checkGpsEnabled(): Boolean {
        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val wifiLocationEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        return gpsLocationEnabled || wifiLocationEnabled
    }
}