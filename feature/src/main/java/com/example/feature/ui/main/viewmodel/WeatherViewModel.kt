package com.example.feature.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.core.domain.entity.Weather
import com.daniil.core.domain.usecase.GetWeatherByCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.daniil.core.tools.Result

@HiltViewModel
class WeatherViewModel @Inject constructor(
private val getWeatherByCityUseCase: GetWeatherByCityUseCase,
) : ViewModel() {

    private val <T> T.exshausative :T
        get() = this

    private val eventChannel = Channel<Event>()
    val event = eventChannel.receiveAsFlow()

    private val _weather = MutableLiveData<Weather?>()
    val weather: LiveData<Weather?>
        get() = _weather

    suspend fun getWeatherByCity(city: String){
       getWeatherByCityUseCase(city = city).let {result ->
           when(result){
               is Result.Success ->{
                    _weather.value = result.data
               }
               is Result.Error ->{
                   sendShowWeatherEvent(result.exception.message ?: "Не удалось получить данные")
               }
               else ->{}
           }
       }.exshausative
    }

    private fun sendShowWeatherEvent(error: String) = viewModelScope.launch{
        eventChannel.send(Event.ShowWeatherError(error))
    }

    sealed class Event{
        data class ShowWeatherError(val error: String) : Event()
    }
}