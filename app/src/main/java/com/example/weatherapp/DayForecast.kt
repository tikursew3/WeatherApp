package com.example.weatherapp

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DayForecast (
    @Json(name = "dt") val date: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: ForecastTemp,
    val weather:List<WeatherForecast>,
    val pressure: Float,
    val humidity: Int,
    ): Parcelable