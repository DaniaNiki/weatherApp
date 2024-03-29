package com.daniil.weather.navigation

import com.daniil.weather.R

sealed class Screens (val route:String, val icon:Int, val title:String){

    object Main : Screens("main", com.example.feature.R.drawable.ic_weather_48, "Погода")
    object Settings : Screens("Settings", com.example.feature.R.drawable.ic_settings_48, "Настройки")

}
