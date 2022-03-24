package com.example.weatherapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastTemp(
    val day: Float,
    val max: Float,
    val min: Float,
): Parcelable