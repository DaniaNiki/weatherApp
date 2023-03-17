package com.daniil.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val location: Location,
    val currentWeather: CurrentWeather
):Parcelable
