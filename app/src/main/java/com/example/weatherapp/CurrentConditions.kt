package com.example.weatherapp

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


data class CurrentConditions(
    @Json(name = "weather") val weather: List<WeatherCondition>,
    @Json(name = "name") val cityName: String,
    @Json(name = "main") val main: Currents,


    )



