package com.daniil.core.data.remote.service

import com.daniil.core.data.remote.responce.WeatherApiResponse
import com.daniil.core.data.remote.source.*
import retrofit2.Response
import retrofit2.http.*

interface Webservice {

  @GET(BASE_URL +"current.json")
  suspend fun getWeatherWithCity(
        @Query(CITY) city:String,
        @Query(LANG) lang:String,
        @Query(KEY) apiKey:String,
        @Query(DAYS) days:String,
        @Query(AIR_QUALITY) airQuality:String,
        ): Response<WeatherApiResponse>

    companion object {
        const val BASE_URL = "http://api.weatherapi.com/v1/"
    }
}