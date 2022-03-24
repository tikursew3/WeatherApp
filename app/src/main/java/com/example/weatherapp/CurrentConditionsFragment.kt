package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentCurrentConditionsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrentConditionsFragment: Fragment(R.layout.fragment_current_conditions) {
    private lateinit var fragmentBinding: FragmentCurrentConditionsBinding

    @Inject lateinit var viewModel: MainViewModel



   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding = FragmentCurrentConditionsBinding.bind(view)

        viewModel.currentConditions.observe(viewLifecycleOwner) {
            currentConditions -> bindData(currentConditions)
        }
        viewModel.loadData()



        val actionButton = view.findViewById<Button>(R.id.button)
        actionButton.setOnClickListener {
            val fm: FragmentManager = requireActivity().supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            val fragment = ForecastFragment()
            ft.replace(R.id.fragment_container_view, fragment, "ForecastFragment" )
            ft.commit()

        }



       val navHostFragment =
           childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
       val navController = navHostFragment.navController




       fragmentBinding.button.setOnClickListener {
            navigateToForecast()
        }



    }
    private fun navigateToForecast() {

       val action = CurrentConditionsFragmentDirections.actionCurrentConditionsFragmentToForecastFragment()
        findNavController().navigate(action)

    }

    @SuppressLint("StringFormatInvalid")
    private fun bindData(currentConditions: CurrentConditions) {
        fragmentBinding.cityName.text = currentConditions.name.toString()
        fragmentBinding.temprature.text = getString(R.string.temperature, currentConditions.main.temp.toInt())

        fragmentBinding.feelsLike.text = getString(R.string.feels_like,currentConditions.main.feelsLike.toInt())
        fragmentBinding.low.text = getString(R.string.low, currentConditions.main.tempMin.toInt())
        fragmentBinding.high.text = getString(R.string.high, currentConditions.main.tempMax.toInt())
        fragmentBinding.humidity.text = getString(R.string.humidity, currentConditions.main.humidity.toInt())

        fragmentBinding.pressure.text = getString(R.string.pressure, currentConditions.main.pressure.toInt())
        val iconName = currentConditions.weather.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(fragmentBinding.conditionIcon)



    }


}