package com.daniil.core.data.remote.responce

import com.google.gson.annotations.SerializedName

data class WeatherDto(
 @field:SerializedName("id") val id: Int,
 @field:SerializedName("numberPhone") val numberPhone: String,
 @field:SerializedName("token") val token: String?
)
