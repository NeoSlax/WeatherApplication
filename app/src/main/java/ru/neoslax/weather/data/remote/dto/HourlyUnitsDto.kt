package ru.neoslax.weather.data.remote.dto


import com.squareup.moshi.Json

data class HourlyUnitsDto(
    @Json(name = "relativehumidity_2m")
    val relativehumidity2m: String,
    @Json(name = "temperature_2m")
    val temperature2m: String,
    @Json(name = "time")
    val time: String,
    @Json(name = "weathercode")
    val weathercode: String
)