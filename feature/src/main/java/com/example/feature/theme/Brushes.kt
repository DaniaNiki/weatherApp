package com.example.feature.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode

val morningGradient = Brush.verticalGradient(
    colors = morningWeather, // Gradient colors
    tileMode = TileMode.Clamp
)

val dayGradient = Brush.verticalGradient(
    colors = dayWeather, // Gradient colors
    tileMode = TileMode.Clamp
)

val nightGradient = Brush.verticalGradient(
    colors = nightWeather, // Gradient colors
    tileMode = TileMode.Clamp
)

val mainScreenGradient = Brush.linearGradient(
    colors = mainScreenBackground,
    tileMode = TileMode.Decal
)

