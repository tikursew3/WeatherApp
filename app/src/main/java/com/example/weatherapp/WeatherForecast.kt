package com.example.weatherapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherForecast (
    val main: String,
    val icon: String,
    ): Parcelable

