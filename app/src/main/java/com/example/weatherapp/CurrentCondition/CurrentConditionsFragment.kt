package com.example.weatherapp.CurrentCondition

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.weatherapp.model.Coordinates
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentCurrentConditionsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrentConditionsFragment: Fragment(R.layout.fragment_current_conditions) {
    private lateinit var fragmentViewBinding: FragmentCurrentConditionsBinding
    private val args: CurrentConditionsFragmentArgs by navArgs()
    @Inject lateinit var viewModel: MainViewModel




   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.current_conditions)
       fragmentViewBinding = FragmentCurrentConditionsBinding.bind(view)

       fragmentViewBinding.forecastButton.setOnClickListener {
           viewModel.forecastButtonClicked()
       }

       viewModel.onViewCreated(args.currentConditions)

       viewModel.navigateToForecast.observe(viewLifecycleOwner) {
           it?.let { coordinates -> navigateToForecast(coordinates)}
       }
       viewModel.viewState.observe(viewLifecycleOwner) {
           bindData(it)
       }

       requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
           findNavController().navigateUp()
       }



    }


    @SuppressLint("StringFormatInvalid")
    private fun bindData(state: MainViewModel.State) {
        fragmentViewBinding.cityName.text = state.currentConditions?.cityName

        fragmentViewBinding.temprature.text = context?.getString(
            R.string.temperature,
            state.currentConditions?.main?.temperature)


        fragmentViewBinding.feelsLike.text =  context?.getString(
            R.string.feels_like,
            state.currentConditions?.main?.feelsLike)

        fragmentViewBinding.low.text = context?.getString(
            R.string.low,
            state.currentConditions?.main?.tempMin)

        fragmentViewBinding.high.text = context?.getString(
            R.string.high,
            state.currentConditions?.main?.tempMax)

        fragmentViewBinding.humidity.text = context?.getString(
            R.string.humidity,
            state.currentConditions?.main?.humidity)


        fragmentViewBinding.pressure.text = context?.getString(
            R.string.pressure,
            state.currentConditions?.main?.pressure)

        val iconName = state.currentConditions?.weather?.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        iconName?.let {
            Glide.with(fragmentViewBinding.conditionIcon)
                .load(iconUrl)
                .into(fragmentViewBinding.conditionIcon)
        }
    }

    private fun navigateToForecast(coordinates: Coordinates) {
        val action =
            CurrentConditionsFragmentDirections
                .actionCurrentConditionsFragmentToForecastFragment(coordinates)
        findNavController().navigate(action)

    }


}