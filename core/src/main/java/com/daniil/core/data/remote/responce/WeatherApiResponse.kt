package com.daniil.core.data.remote.responce

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @field:SerializedName("location") val location: LocationDto,
    @field:SerializedName("current") val current: CurrentHourDto,
)
