package com.daniil.core.data.remote.source

import android.content.Context
import com.daniil.core.data.remote.responce.WeatherApiResponse
import com.daniil.core.data.remote.service.Webservice
import com.daniil.core.data.remote.service.Webservice.Companion.BASE_URL
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import com.daniil.core.data.tools.Result
import java.util.Locale
import kotlin.Exception


//param names
const val DAYS = "days"
const val LANG = "lang"
const val KEY = "key"
const val CITY = "q"
const val AIR_QUALITY = "aqi"

@Singleton
class RemoteDataSource @Inject constructor(
    private val service: Webservice,
    @ApplicationContext private val context: Context
) {

    private val apiKey = "46849f1b70e1493b82081332231503"

   suspend fun getWeatherByCity(city:String) : Result<WeatherApiResponse>{
       return try {
            val response = service.getWeatherWithCity(
                city = city.toString(),
                lang = Locale.getDefault().country,
                apiKey = apiKey,
                days = "1",
                airQuality = "yes"
            )
            if (!response.isSuccessful){
                Result.Error(Exception("Не удалось получить данные о погоде"))
            }
            val body = response.body() ?: throw IllegalStateException("Пустое тело ответа")

            Result.Success(body)

        } catch (ex:Exception){
            Result.Error(ex)
        }
    }

}