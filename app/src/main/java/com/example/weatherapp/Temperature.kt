package com.example.weatherapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Temperature(
    val dayTemp: Float,
    val min: Float,
    val max: Float,
): Parcelable