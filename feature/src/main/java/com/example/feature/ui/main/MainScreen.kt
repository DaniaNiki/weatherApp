package com.example.feature.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.feature.ui.main.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

@Composable
fun SetMainScreen(viewModel:WeatherViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Info(viewModel)
    }
}

@Composable
fun Info(viewModel: WeatherViewModel) {
    val weather by viewModel.weather.observeAsState()
    LaunchedEffect(Unit){
        this.launch {
            viewModel.getWeatherByCity("Jaroslavl")
        }
    }
  Column(modifier = Modifier.fillMaxWidth(0.8f), horizontalAlignment = Alignment.CenterHorizontally) {
      Row(horizontalArrangement = Arrangement.Center) {
          Text("тут будет иконка glide")
      }
      Row(horizontalArrangement = Arrangement.Center) {
          Text(text = weather?.location?.city ?: "загрузка")
      }
      Row(horizontalArrangement = Arrangement.Center) {
          Text(text = weather?.currentWeather?.temperature.toString() ?: "загрузка")
      }
  }
}