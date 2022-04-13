package com.example.weatherapp.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.extensions.isValidZipCode
import com.example.weatherapp.Api
import com.example.weatherapp.model.CurrentConditions
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel  @Inject constructor(private val service: Api): ViewModel()  {

    private var searchText = ""
    private val _events = MutableLiveData<Event>()
    val events: LiveData<Event> = _events

    private val _state = MutableLiveData(State.DEFAULT)
    val state: LiveData<State> = _state

    fun onViewCreated() {
        _events.value = Event.ViewCreated
        _state.value = State.DEFAULT
    }

    fun searchButtonClicked() = viewModelScope.launch {
        try {
            val response = service.getCurrentConditions(searchText)
            _events.value = Event.NavigateToCurrentConditions(response)
        } catch (ex: Exception) {
            _events.value = Event.SearchError
        }
    }




    fun updateZipCode(searchText: String) {
        this.searchText = searchText
        _state.value = _state.value?.copy(
            searchButtonEnabled = this.searchText.isValidZipCode()
        )
    }

    data class State(
        val searchButtonEnabled: Boolean,

    ) {
        companion object {
            val DEFAULT: State = State(
                searchButtonEnabled = false

            )
        }
    }

    sealed class Event {
        data class NavigateToCurrentConditions(val currentConditions: CurrentConditions): Event()
        object SearchError: Event()
        object ViewCreated: Event()

    }
}
