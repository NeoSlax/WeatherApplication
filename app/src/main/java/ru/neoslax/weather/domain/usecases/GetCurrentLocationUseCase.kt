package ru.neoslax.weather.domain.usecases

import android.location.Location
import ru.neoslax.weather.di.MockData
import ru.neoslax.weather.di.ProdData
import ru.neoslax.weather.domain.repository.LocationTracker
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    @ProdData
    private val locationTracker: LocationTracker
) {
    suspend operator fun invoke(): Location? {
        return locationTracker.gerCurrentLocation()
    }
}