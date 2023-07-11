package com.daniil.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val city:String,
    val region:String,
    val country:String,
    val latitude:Double,
    val longitude:Double,
    val timeZoneId:String,
    val localTime:String,

):Parcelable
