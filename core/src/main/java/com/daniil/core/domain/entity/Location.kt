package com.daniil.core.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val city:String,
    val region:String,
    val country:String,
    val latitude:Float,
    val longitude:Float
):Parcelable
