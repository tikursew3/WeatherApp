package com.example.weatherapp.CurrentCondition

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.weatherapp.model.Coordinates
import com.example.weatherapp.model.CurrentConditions
import javax.inject.Inject

class MainViewModel @Inject constructor(): ViewModel() {

    private lateinit var currentConditions: CurrentConditions
    private val _viewState = MutableLiveData(State.DEFAULT)
    val viewState: LiveData<State> = _viewState


    private val _navigateToForecast = MutableLiveData<Coordinates?>()
    val navigateToForecast: LiveData<Coordinates?> = _navigateToForecast



    fun onViewCreated(currentConditions: CurrentConditions) {
        this.currentConditions = currentConditions
        _viewState.value = _viewState.value?.copy(currentConditions = currentConditions)
        _navigateToForecast.value = null
    }


    fun forecastButtonClicked() {
        _navigateToForecast.value = currentConditions.coordinates
    }

    data class State(
        val currentConditions: CurrentConditions?
    ) {
        companion object {
            internal val DEFAULT = State(null)
        }
    }
}