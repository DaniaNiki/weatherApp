package com.daniil.weather.application

import android.app.Application
import com.daniil.core.data.remote.source.RemoteDataSource
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import com.daniil.core.data.tools.Result
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var remoteDataSource: RemoteDataSource

    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        Timber.plant()
        getAllUsers()
    }

   private fun getAllUsers() = coroutineScope.launch {
           remoteDataSource.getWeatherByCity(city ="Jaroslavl").let { result ->
               when(result){
                   is Result.Success ->{
                      println(result.data.toString())
                   }
                   is Result.Error ->{
                       println(result.exception.message)
                   }
               }
           }
    }
}