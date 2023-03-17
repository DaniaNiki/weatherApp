package com.daniil.core.domain.repository

import com.daniil.core.domain.entity.Weather
import com.daniil.core.tools.Result

interface WeatherRepository {
    suspend fun getWeather(city: String) :Result<Weather?>
}