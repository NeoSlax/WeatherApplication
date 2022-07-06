package ru.neoslax.weather.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.neoslax.weather.data.remote.dto.WeatherDataDto

interface WeatherApi {
    @GET(WEATHER_ENDPOINT)
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherDataDto

    companion object {
        private const val WEATHER_ENDPOINT =
            "v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl"
        const val BASE_URL = "https://api.open-meteo.com/"
    }
}