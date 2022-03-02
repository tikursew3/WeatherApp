package com.example.weatherapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ActivityForecastBinding
import com.example.weatherapp.databinding.ActivityMainBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class ForecastActivity : AppCompatActivity() {


    private val apikey = "f46c384220f36eba4185c54a1c0b95b4"

    private lateinit var binding: ActivityForecastBinding
    @Inject lateinit var viewModel:  ForecastViewModel





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        binding = ActivityForecastBinding.inflate(layoutInflater)

        setContentView(binding.root)


    }


    override fun onResume() {
        super.onResume()
        viewModel.forecastConditions.observe(this) {forecastConditions ->
            bindData(forecastConditions)
        }

        viewModel.loadData()

    }
    @SuppressLint("StringFormatInvalid")
    private fun bindData(forecast: Forecast) {
        binding.recyclerView.adapter = MyAdapter(forecast.list)

    }
}