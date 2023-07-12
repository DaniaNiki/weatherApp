package com.daniil.weather.navigation

import com.daniil.weather.R

sealed class Screens (val route:String, val icon:Int, val title:String){

    object Main : Screens("main", com.example.feature.R.drawable.weather_icon_48, "Текущая погода")
    object Settings : Screens("Settings", com.example.feature.R.drawable.icon_settings_48, "Настройки")

}
