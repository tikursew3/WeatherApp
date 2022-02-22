package com.example.weatherapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private val apikey = "f46c384220f36eba4185c54a1c0b95b4"
    private lateinit var api: Api
    private lateinit var cityName: TextView
    private lateinit var currentTemp: TextView
    private lateinit var feelsLike: TextView
    private lateinit var low: TextView
    private lateinit var high: TextView
    private lateinit var humidity: TextView
    private lateinit var pressure: TextView
    private lateinit var conditionIcon: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityName = findViewById(R.id.city_name)
        currentTemp = findViewById(R.id.temprature)
        feelsLike = findViewById(R.id.feels_like)
        low = findViewById(R.id.low)
        high = findViewById(R.id.high)
        humidity = findViewById(R.id.humidity)
        pressure = findViewById(R.id.pressure)
        conditionIcon = findViewById(R.id.condition_icon)




        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        api = retrofit.create(Api::class.java)





        val actionButton = findViewById<Button>(R.id.button)
        actionButton.setOnClickListener {
            val intent = Intent(this, ForecastActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val call: Call<CurrentConditions> = api.getCurrentConditions("55118")
        call.enqueue(object : Callback<CurrentConditions> {
            override fun onResponse(
                call: Call<CurrentConditions>,
                response: Response<CurrentConditions>
            ) {
               val currentConditions = response.body()
                currentConditions?.let { bindData(it) }

            }

            override fun onFailure(call: Call<CurrentConditions>, t: Throwable) {

            }

        })
    }
    @SuppressLint("StringFormatInvalid")
    private fun bindData(currentConditions: CurrentConditions) {
        cityName.text = currentConditions.name.toString()
        currentTemp.text = getString(R.string.temperature, currentConditions.main.temp.toInt())

        feelsLike.text = getString(R.string.feels_like,currentConditions.main.feelsLike.toInt())
        low.text = getString(R.string.low, currentConditions.main.tempMin.toInt())
        high.text = getString(R.string.high, currentConditions.main.tempMax.toInt())
        humidity.text = getString(R.string.humidity, currentConditions.main.humidity.toInt())



        pressure.text = getString(R.string.pressure, currentConditions.main.pressure.toInt())
        val iconName = currentConditions.weather.firstOrNull()?.icon
        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(conditionIcon)



    }

}
