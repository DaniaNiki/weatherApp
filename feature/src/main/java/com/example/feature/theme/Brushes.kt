package com.example.feature.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode

val morningScreenGradient = Brush.linearGradient(
    colors = morningWeather, // Gradient colors
    tileMode = TileMode.Decal
)

val dayScreenGradient = Brush.linearGradient(
    colors = mainScreenBackground, // Gradient colors
    tileMode = TileMode.Decal
)

val eveningScreenGradient = Brush.linearGradient(
    colors = eveningWeather, // Gradient colors
    tileMode = TileMode.Decal
)

val nightScreenGradient = Brush.linearGradient(
    colors = nightWeather, // Gradient colors
    tileMode = TileMode.Decal
)

