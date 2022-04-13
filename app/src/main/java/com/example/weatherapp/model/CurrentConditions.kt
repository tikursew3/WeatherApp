package com.example.weatherapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentConditions(
    @Json(name = "weather") val weather: List<WeatherCondition>,
    @Json(name = "name") val cityName: String,
    @Json(name = "main") val main: Currents,
    @Json(name = "coord") val coordinates: Coordinates,

    ): Parcelable

@Parcelize
data class Currents (
    @Json(name = "temp") val temperature: Double,
    @Json(name = "feels_like") val feelsLike: Float,
    @Json(name = "temp_min") val tempMin: Float,
    @Json(name = "temp_max") val tempMax: Float,
    @Json(name = "pressure") val pressure: Double,
    @Json(name = "humidity") val humidity: Double,
):Parcelable

@Parcelize
data class WeatherCondition(
    @Json(name = "main") val conditionName: String,
    @Json(name = "icon") val icon: String,
): Parcelable
