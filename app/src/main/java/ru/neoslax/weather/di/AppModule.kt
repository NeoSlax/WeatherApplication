package ru.neoslax.weather.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.neoslax.weather.data.remote.WeatherApi
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder().baseUrl(WeatherApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFusedLocationTracker(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }
}