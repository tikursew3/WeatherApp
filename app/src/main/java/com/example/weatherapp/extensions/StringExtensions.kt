package com.example.weatherapp.extensions

fun String?.isValidZipCode(): Boolean {
    return this?.length == 5 && this.all { it.isDigit() }
}