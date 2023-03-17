package com.daniil.core.di

import com.daniil.core.data.repository.WeatherRepositoryImpl
import com.daniil.core.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindWeatherRepository(weatherRepositoryImpl:WeatherRepositoryImpl) : WeatherRepository
}