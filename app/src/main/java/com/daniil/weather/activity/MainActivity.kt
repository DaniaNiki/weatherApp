package com.daniil.weather.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.daniil.weather.navigation.BottomNavigationBar
import com.daniil.weather.navigation.NavigationComponent
import com.example.feature.theme.WeatherTheme
import com.example.feature.theme.createBackgroundGradient
import com.example.feature.theme.dayScreenGradient
import com.example.feature.theme.eveningScreenGradient
import com.example.feature.theme.morningScreenGradient
import com.example.feature.theme.nightScreenGradient
import com.example.feature.util.getLocalTime
//import com.example.feature.theme.mainScreenGradient
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.TimeZone

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("2f6e832b-711e-42d7-ae58-90e368e2a01a")
        MapKitFactory.initialize(this)
        MapKitFactory.getInstance().onStart()
        setContent {
            val navController = rememberNavController()
            WeatherTheme {
                Scaffold(
                    content = { _ ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize(),
                            color = MaterialTheme.colors.background,
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .createBackgroundGradient(getBrushByHours()),
                                ) {
                                    NavigationComponent(navController = navController)
                                }
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController = navController)
                    })
            }
        }

    }
}

private fun getBrushByHours() = when (LocalDateTime.now().hour) {
    in 6..11 -> morningScreenGradient // с 6 до 12 часов утра - белый цвет
    in 12..17 -> dayScreenGradient // с 12 до 18 часов дня - серый цвет
    in 18..20 -> eveningScreenGradient // с 12 до 18 часов дня - серый цвет
    in 21..24 -> nightScreenGradient // с 18 до 24 часов вечера - светло-серый цвет
    else -> nightScreenGradient// ночью - черный цвет

}