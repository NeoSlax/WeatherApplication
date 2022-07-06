package ru.neoslax.weather.domain.usecases

import ru.neoslax.weather.domain.entities.WeatherInfo
import ru.neoslax.weather.domain.repository.WeatherRepository
import ru.neoslax.weather.domain.util.Resource
import javax.inject.Inject

class GetWeatherDataUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Resource<WeatherInfo> {
        return repository.getWeatherData(latitude, longitude)
    }
}