package com.daniil.core.data.remote.entity

import com.google.gson.annotations.SerializedName

data class LocationDto(
    @field:SerializedName("name") val city: String,
    @field:SerializedName("region") val region: String,
    @field:SerializedName("country") val country: String,
    @field:SerializedName("lat") val latitude: Double,
    @field:SerializedName("lon") val longitude: Double,
    @field:SerializedName("tz_id") val timeZoneID: String,
    @field:SerializedName("localtime") val localTime: String,

)
