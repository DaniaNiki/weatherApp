package com.daniil.weather.navigation

sealed class Screens (val route:String){

    object Main :Screens("main")

}
