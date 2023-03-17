package com.daniil.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.ui.main.SetMainScreen
import com.example.feature.ui.main.viewmodel.WeatherViewModel

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Main.route){
        composable(route = Screens.Main.route){
            val viewModel = hiltViewModel<WeatherViewModel>()
            SetMainScreen(viewModel)
        }
    }
}