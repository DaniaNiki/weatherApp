package com.example.feature.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.TypefaceCompat.ResourcesCallbackAdapter
import com.example.feature.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = mavenProFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = Color.DarkGray,
    ),
    body2 = TextStyle(
        fontFamily = mavenProFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.DarkGray,
    ),
    h1 = TextStyle(
        fontFamily = mavenProFonts,
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)