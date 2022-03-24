package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val service: Api): ViewModel() {
    private val _forecastConditions = MutableLiveData<Forecast>()
    val forecastConditions: LiveData<Forecast>
        get() = _forecastConditions

    fun loadData() = runBlocking{
        launch {_forecastConditions.value = service.getForecast("73071")}
    }
}