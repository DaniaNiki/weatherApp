package com.example.feature.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode

val morningGradient = Brush.verticalGradient(
    colors = morningWeather, // Gradient colors
    startY = 0f, // Starting Y position of the gradient
    endY = 500f, // Ending Y position of the gradient
    tileMode = TileMode.Clamp
)

val dayGradient = Brush.verticalGradient(
    colors = dayWeather, // Gradient colors
    startY = 0f, // Starting Y position of the gradient
    endY = 500f, // Ending Y position of the gradient
    tileMode = TileMode.Clamp
)

val nightGradient = Brush.verticalGradient(
    colors = dayWeather, // Gradient colors
    startY = 0f, // Starting Y position of the gradient
    endY = 500f, // Ending Y position of the gradient
    tileMode = TileMode.Clamp
)

