package ru.neoslax.weather.data.mappers

import ru.neoslax.weather.data.remote.dto.WeatherDataDto
import ru.neoslax.weather.domain.entities.WeatherData
import ru.neoslax.weather.domain.entities.WeatherInfo
import ru.neoslax.weather.domain.util.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class WeatherDataIndexed(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return hourly.time.mapIndexed { index, time ->

        val time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME)
        val temperature = hourly.temperatures[index]
        val pressure = hourly.pressures[index]
        val windSpeed = hourly.windSpeeds[index]
        val humidity = hourly.humidities[index]
        val weatherType = WeatherType.fromWMO(hourly.weatherCodes[index])
        WeatherDataIndexed(
            index = index,
            data =
            WeatherData(
                time = time,
                temperature = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = weatherType
            )
        )
    }.groupBy { weatherDataIndexed ->
        weatherDataIndexed.index / 24
    }.mapValues {
        it.value.map { weatherDataIndexed -> weatherDataIndexed.data }
    }
}

fun WeatherDataDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap =  this.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find { weatherData ->
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        weatherData.time.hour == hour
    }

    return WeatherInfo(weatherDataPerDay = weatherDataMap,
    currentWeatherData = currentWeatherData)
}