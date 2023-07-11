package com.example.feature.ui.main.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun Moon(modifier: Modifier = Modifier) {
    Canvas(modifier) {

        val canvasWidth = size.width  // Width of canvas
        val canvasHeight = size.height  // Canvas height

        val strokeWidth = size.width / 35
        val strokeHeight = size.height / 35

        // draw circle
        // cylindrical wide
        val outerWidth = canvasWidth - strokeWidth
        val outerHeight = canvasHeight - strokeHeight
        val shadowWidth = outerWidth * sqrt(3.0) / 2
        val shadowHeight = outerHeight * sqrt(3.0) / 2
        drawArc(
            color = Color.Black,
            startAngle = 40F,
            sweepAngle = 245f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            ),
            topLeft = Offset(strokeWidth / 3f, strokeHeight / 2),
            size = Size(outerWidth, outerHeight),
        )
        drawArc(
            color = Color.Black,
            startAngle = 60F,
            sweepAngle = 205f,
            useCenter = false,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            ),
            topLeft = Offset(strokeWidth * 8, outerWidth / 24.0F),
            size = Size(shadowWidth.toFloat(), shadowHeight.toFloat()),
        )
    }
}

@Preview
@Composable
fun previewMoon() {
    Moon(Modifier.size(50.dp))
}