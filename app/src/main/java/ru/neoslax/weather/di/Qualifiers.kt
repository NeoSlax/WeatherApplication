package ru.neoslax.weather.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MockData

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ProdData
