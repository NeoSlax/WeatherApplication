package ru.neoslax.weather.domain.entities

import ru.neoslax.weather.domain.util.WeatherType
import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperature: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
