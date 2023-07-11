package com.example.feature.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocaleDateTime(dateFormat: String = "yyyy-MM-dd HH:mm", timeZone: TimeZone = TimeZone.getTimeZone("Europe/Moscow")): String {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    parser.toLocalizedPattern()
    val formatter = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(parser.parse(this)) ?: LocalDateTime.now().toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.getLocalTime(dateFormat: String = "yyyy-MM-dd HH:mm", timeZone: TimeZone = TimeZone.getTimeZone("Europe/Moscow")): Int {
    val parser = SimpleDateFormat(dateFormat, Locale.getDefault())
    parser.timeZone = timeZone
    parser.toLocalizedPattern()
    val formatter = SimpleDateFormat("HH", Locale.getDefault())
    formatter.timeZone = timeZone
    return formatter.format(parser.parse(this)).toInt()
}