package com.example.weatherapp

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast (
    @Json(name = "list") val list: List<DayForecast>,
): Parcelable

@Parcelize
data class DayForecast (
    @Json(name = "dt") val date: Long,
    @Json(name = "sunrise") val sunrise: Long,
    @Json(name = "sunset") val sunset: Long,
    @Json(name = "temp") val temp: ForecastTemp,
    @Json(name = "weather")val weather:List<WeatherForecast>,
    @Json(name = "pressure") val pressure: Double,
    @Json(name = "humidity") val humidity: Double,
): Parcelable

@Parcelize
data class ForecastTemp(
    @Json(name = "day") val day: Double,
    @Json(name = "max") val max: Double,
    @Json(name = "min") val min: Double,
): Parcelable

@Parcelize
data class WeatherForecast (
    val main: String,
    @Json(name = "icon") val icon: String,
): Parcelable