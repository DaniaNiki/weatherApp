package com.daniil.core.data.remote.entity

import com.google.gson.annotations.SerializedName

data class CurrentHourDto(
    @field:SerializedName("temp_c") val temperatureCelsius: Float,
    @field:SerializedName("condition") val condition: ConditionDto,
    @field:SerializedName("wind_kph") val windSpeed: Float,

    )

data class ConditionDto(
    @field:SerializedName("icon") val temperatureIcon: String
)
