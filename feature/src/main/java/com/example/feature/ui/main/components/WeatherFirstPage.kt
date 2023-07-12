package com.example.feature.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daniil.core.domain.entity.Weather
import com.example.feature.theme.Typography
import com.example.feature.util.toLocaleDateTime
import java.util.*

@Composable
fun WeatherFirstPage(weather: Weather) {
    Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
            Text(text = weather.location.localTime.toLocaleDateTime(timeZone = TimeZone.getTimeZone(weather.location.timeZoneId)) ?: "загрузка", style = Typography.body1)
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = weather.location.city ?: "загрузка",style = Typography.body1)
        }
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "Температура: ${ weather.currentWeather.temperature.toString() }" ?: "загрузка",style = Typography.body1)
        }
    }
}