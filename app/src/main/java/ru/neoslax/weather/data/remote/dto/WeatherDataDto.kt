package ru.neoslax.weather.data.remote.dto


import com.squareup.moshi.Json

data class WeatherDataDto(
    @Json(name = "elevation")
    val elevation: Double,
    @Json(name = "generationtime_ms")
    val generationtimeMs: Double,
    @Json(name = "hourly")
    val hourly: HourlyDto,
    @Json(name = "hourly_units")
    val hourlyUnits: HourlyUnitsDto,
    @Json(name = "latitude")
    val latitude: Double,
    @Json(name = "longitude")
    val longitude: Double,
    @Json(name = "utc_offset_seconds")
    val utcOffsetSeconds: Int
)