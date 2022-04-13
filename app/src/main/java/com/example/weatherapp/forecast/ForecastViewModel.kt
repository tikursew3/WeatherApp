package com.example.weatherapp.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Api
import com.example.weatherapp.model.Coordinates
import com.example.weatherapp.Forecast
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForecastViewModel @Inject constructor(private val api: Api): ViewModel() {
    private val _viewState = MutableLiveData(State.DEFAULT)
    val viewState: LiveData<State> = _viewState

    fun onViewCreated(coordinates: Coordinates) {
        fetchForecast(coordinates)
    }

    private fun fetchForecast(coordinates: Coordinates) = viewModelScope.launch{
        val forecast = api.getForecast(
            coordinates.latitude.toString(),
            coordinates.longitude.toString()
        )
        _viewState.value = _viewState.value?.copy(forecast = forecast)
    }

    data class State(
        val forecast: Forecast?
    ) {
        companion object {
            val DEFAULT = State(null)
        }
    }


}