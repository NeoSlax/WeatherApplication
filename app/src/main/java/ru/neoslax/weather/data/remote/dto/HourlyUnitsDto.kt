package ru.neoslax.weather.data.remote.dto


import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
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