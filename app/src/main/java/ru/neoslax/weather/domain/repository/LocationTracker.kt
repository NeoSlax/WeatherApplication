package ru.neoslax.weather.domain.repository

import android.location.Location

interface LocationTracker  {

    //TODO wrap with Resource class
    suspend fun gerCurrentLocation(): Location?
}