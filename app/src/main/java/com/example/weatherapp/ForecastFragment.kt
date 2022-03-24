package com.example.weatherapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ForecastFragment: Fragment(R.layout.fragment_forecast) {
    private lateinit var fragmentBinding: FragmentForecastBinding

    @Inject lateinit var viewModel: ForecastViewModel



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding= FragmentForecastBinding.bind(view)

        viewModel.forecastConditions.observe(viewLifecycleOwner) {
            forecastConditions -> bindData(forecastConditions)
        }
        viewModel.loadData()





    }
    @SuppressLint("StringFormatInvalid")
    private fun bindData(forecast: Forecast) {
        fragmentBinding.recyclerView.adapter = MyAdapter(forecast.list)

    }

}