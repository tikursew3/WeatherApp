package com.example.weatherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel  @Inject constructor(private val service: Api): ViewModel()  {
    private val _enableButton = MutableLiveData<Boolean>( false)
    private var zipCode: String? = null
    val enableButton: LiveData<Boolean>
            get() = _enableButton

    fun updateZipCode(zipCode: String) {
        if(zipCode != this.zipCode) {
            _enableButton.value = isValidZipCode(zipCode)
        }

    }
    private fun isValidZipCode(zipCode: String): Boolean {
        return zipCode.length == 5 && zipCode.all { it.isDigit() }
    }
    fun submitButtonClicked() {

    }
}