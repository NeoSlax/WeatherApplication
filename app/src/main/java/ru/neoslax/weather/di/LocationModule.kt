package ru.neoslax.weather.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.neoslax.weather.data.repository.LocationTrackerImpl
import ru.neoslax.weather.data.repository.MockLocationTrackerImpl
import ru.neoslax.weather.domain.repository.LocationTracker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocationModule {

    @Binds
    @Singleton
    @MockData
    fun bindMockLocationTracker(locationTracker: MockLocationTrackerImpl): LocationTracker

    @Binds
    @Singleton
    @ProdData
    fun bindProdLocationTracker(locationTracker: LocationTrackerImpl): LocationTracker
}