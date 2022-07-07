package ru.neoslax.weather.data.repository

import android.app.Application
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.delay
import ru.neoslax.weather.domain.repository.LocationTracker
import javax.inject.Inject

class MockLocationTrackerImpl @Inject constructor(
    val application: Application
) : LocationTracker {

    override suspend fun gerCurrentLocation(): Location? {
        delay(2000L)
        val location = Location(LocationManager.GPS_PROVIDER).apply {
            latitude = 55.0
            longitude = 55.0
            time = System.currentTimeMillis()
        }
        Toast.makeText(
            application,
            "This is mock location: $location",
            Toast.LENGTH_SHORT
        ).show()
        return location
    }
}