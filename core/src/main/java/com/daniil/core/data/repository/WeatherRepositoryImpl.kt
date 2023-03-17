package com.daniil.core.data.repository

import com.daniil.core.data.remote.source.RemoteDataSource
import com.daniil.core.domain.entity.Weather
import com.daniil.core.domain.repository.WeatherRepository
import com.daniil.core.tools.toCurrentWeather
import com.daniil.core.tools.Result
import com.daniil.core.tools.toWeather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    WeatherRepository {

    override suspend fun getWeather(city: String): Result<Weather?> {
        remoteDataSource.getWeatherByCity(city).let { result ->
            return when (result) {
                is Result.Success -> {
                    Result.Success(result.data.toWeather())
                }
                is Result.Error -> {
                    Result.Error(Exception(result.exception.message))
                }
                else -> Result.Error(Exception("no city"))
            }
        }
    }

}