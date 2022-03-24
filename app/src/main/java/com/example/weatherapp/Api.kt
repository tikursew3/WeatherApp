package com.example.weatherapp


import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    suspend fun getCurrentConditions(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "f46c384220f36eba4185c54a1c0b95b4",
    ): CurrentConditions
    @GET("forecast/daily")
    suspend fun getForecast(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = "f46c384220f36eba4185c54a1c0b95b4",
        @Query("cnt" ) count: String = "16"
    ): Forecast
}