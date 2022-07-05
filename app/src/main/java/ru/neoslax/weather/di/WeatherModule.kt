package ru.neoslax.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.neoslax.weather.data.repository.WeatherRepositoryImpl
import ru.neoslax.weather.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface WeatherModule {

    @Binds
    @Singleton
    fun bindWeatherRepo(weatherRepositoryImpl: WeatherRepositoryImpl): WeatherRepository
}