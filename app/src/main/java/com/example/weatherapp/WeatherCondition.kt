package com.example.weatherapp

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class WeatherCondition(
    @Json(name = "main") val conditionName: String,
    @Json(name = "icon") val icon: String,
)
