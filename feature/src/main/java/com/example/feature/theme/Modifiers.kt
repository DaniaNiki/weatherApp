package com.example.feature.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Brush

fun Modifier.createBackgroundGradient(brush:Brush) = drawWithCache {
    onDrawBehind {
        drawRect(brush = brush)
    }
}