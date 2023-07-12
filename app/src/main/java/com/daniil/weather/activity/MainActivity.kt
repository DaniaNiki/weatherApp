package com.daniil.weather.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.daniil.weather.navigation.BottomNavigationBar
import com.daniil.weather.navigation.NavigationComponent
import com.example.feature.theme.WeatherTheme
import com.example.feature.theme.createBackgroundGradient
import com.example.feature.theme.mainScreenGradient
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("2f6e832b-711e-42d7-ae58-90e368e2a01a")
        MapKitFactory.initialize(this)
        MapKitFactory.getInstance().onStart()
        this.window.statusBarColor = applicationContext.getColor(android.R.color.holo_blue_bright)
        setContent {
            val navController = rememberNavController()
            WeatherTheme {
                Scaffold(
                    modifier = Modifier.createBackgroundGradient(brush = mainScreenGradient),
                    content = { _ ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(brush = mainScreenGradient),
                            color = MaterialTheme.colors.background,
                            content = {
                                NavigationComponent(navController = navController)
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