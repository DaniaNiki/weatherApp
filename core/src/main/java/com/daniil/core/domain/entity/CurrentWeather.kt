package com.daniil.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentWeather(
    val temperature:Float,
    val condition: Condition,
    val windSpeed:Float
):Parcelable
