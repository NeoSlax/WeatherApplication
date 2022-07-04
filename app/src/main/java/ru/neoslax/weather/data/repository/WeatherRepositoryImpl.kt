package ru.neoslax.weather.data.repository

import ru.neoslax.weather.data.mappers.toWeatherInfo
import ru.neoslax.weather.data.remote.WeatherApi
import ru.neoslax.weather.domain.entities.WeatherInfo
import ru.neoslax.weather.domain.repository.WeatherRepository
import ru.neoslax.weather.domain.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(latitude: Double, longitude: Double): Resource<WeatherInfo> {
        return try {
            val weatherDataDto =
                weatherApi.getWeatherData(latitude = latitude, longitude = longitude)
            Resource.Success(weatherDataDto.toWeatherInfo())
        } catch (e: Throwable) {
            e.printStackTrace()
            Resource.Error(
                message = e.toString(),
                data = null
            )
        }
    }
}