package com.example.weatherapp.search

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.CurrentCondition.SearchErrorDialog
import com.example.weatherapp.MainActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.search.SearchViewModel.*
import com.example.weatherapp.search.SearchViewModel.Event.NavigateToCurrentConditions
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.*
import javax.inject.Inject

class SearchFragment: Fragment(R.layout.fragment_search) {



    @RequiresApi(Build.VERSION_CODES.N)
    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                requestLocation()
            }
            else -> {
                //no location access granted
            }
        }
    }
    private lateinit var activity: MainActivity

    private lateinit var fragmentBinding: FragmentSearchBinding
    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.search)


        fragmentBinding = FragmentSearchBinding.bind(view)

        fragmentBinding.searchButton.setOnClickListener { viewModel.searchButtonClicked() }

       fragmentBinding.requestButton.setOnClickListener { viewModel.searchButtonClicked() }

        fragmentBinding.inputZipCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.toString()?.let { viewModel.updateZipCode(it) }
            }
            override fun afterTextChanged(s: Editable?) {
                viewModel.updateZipCode(s.toString())
            }
        })
        viewModel.onViewCreated()
        viewModel.events.observe(viewLifecycleOwner) {
            when (it) {
                is Event.NavigateToCurrentConditions -> navigateToCurrentConditions(it)
                Event.SearchError -> handleSearchError()
                Event.ViewCreated -> {
                }
            }
        }
        viewModel.state.observe(viewLifecycleOwner) { bindView(it) }





    }

    private fun navigateToCurrentConditions(navigateToCurrentConditions: NavigateToCurrentConditions) {
        val action = SearchFragmentDirections.actionSearchFragmentToCurrentConditionsFragment(
            navigateToCurrentConditions.currentConditions
        )
        findNavController().navigate(action)
    }

    private fun bindView(state: SearchViewModel.State) {
        fragmentBinding.searchButton.isEnabled = state.searchButtonEnabled
        //fragmentBinding.requestButton.isEnabled = state.requestButtonEnabled
    }

    private fun handleSearchError() {
        SearchErrorDialog()
            .show(childFragmentManager, SearchErrorDialog.TAG)
    }


    private fun requestLocation() {
        val locationProvider = getFusedLocationProviderClient(activity)
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle("Request")
                    .setTitle("Rationale")
                    .setNeutralButton("Ok") { _, _ ->
                        locationPermissionRequest.launch(
                            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
                        )
                    }
                    .show()
            }
        }else {
        locationPermissionRequest.launch(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)
        )
    }
    }

}


