package com.daniil.weather.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.ui.main.SetMainScreen
import com.example.feature.ui.main.viewmodel.WeatherViewModel
import com.example.feature.ui.settings.SettingsView
import com.example.feature.ui.settings.viewmodel.SettingsViewModel

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Main.route){
        composable(route = Screens.Main.route){
            val viewModel = hiltViewModel<WeatherViewModel>()
            SetMainScreen(viewModel)
        }
        composable(route = Screens.Settings.route){
            val viewModel = hiltViewModel<SettingsViewModel>()
            SettingsView(navController = navController)
        }
    }
}