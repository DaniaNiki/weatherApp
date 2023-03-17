package com.daniil.core.domain.usecase

import com.daniil.core.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(private val weatherRepository:WeatherRepository) {

    suspend operator fun invoke(city:String) = weatherRepository.getWeather(city = city)

}