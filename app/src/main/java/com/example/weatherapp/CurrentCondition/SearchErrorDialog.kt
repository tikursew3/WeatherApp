package com.example.weatherapp.CurrentCondition

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SearchErrorDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog
            .Builder(requireContext())
            .setTitle("Error")
            .setMessage("There was an error search for this ZIP code. please try different ZIP")
            .setPositiveButton("Okay", null)
            .create()
    }
    companion object {
        const val TAG = "SearchErrorDialog"
    }
}