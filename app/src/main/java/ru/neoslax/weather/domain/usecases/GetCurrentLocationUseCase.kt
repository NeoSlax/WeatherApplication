package ru.neoslax.weather.domain.usecases

import android.location.Location
import ru.neoslax.weather.domain.repository.LocationTracker
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val locationTracker: LocationTracker
) {
    suspend operator fun invoke(): Location? {
        return locationTracker.gerCurrentLocation()
    }
}