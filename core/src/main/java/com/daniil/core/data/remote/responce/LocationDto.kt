package com.daniil.core.data.remote.responce

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @field:SerializedName("name") val city: String,
    @field:SerializedName("region") val numberPhone: String,
    @field:SerializedName("country") val country: String,
    @field:SerializedName("lat") val latitude: Float,
    @field:SerializedName("lon") val lontitude: Float,

)
