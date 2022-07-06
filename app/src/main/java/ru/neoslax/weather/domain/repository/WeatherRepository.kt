package ru.neoslax.weather.domain.repository

import ru.neoslax.weather.domain.entities.WeatherData
import ru.neoslax.weather.domain.entities.WeatherInfo
import ru.neoslax.weather.domain.util.Resource

interface WeatherRepository {

    suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo>
}