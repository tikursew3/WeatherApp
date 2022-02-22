package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ForecastActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    private val apikey = "f46c384220f36eba4185c54a1c0b95b4"
    private lateinit var api: Api
    private lateinit var date1: TextView
    private lateinit var temp1: TextView
    private lateinit var high1: TextView
    private lateinit var low1: TextView
    private lateinit var sunrise1: TextView
    private lateinit var sunset1: TextView
    private lateinit var conditionIcon1: ImageView


    private val adapterData = listOf<DayForecast>(
        DayForecast(
            date = 1643805960,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(3F, 60F, 40F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1643892360,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(4F, 55F, 42F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1643978760,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(5F, 32F, 22F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644065160,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(6F, 44F, 34F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644151560,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(7F, 60F, 41F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644237960,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(8F, 60F, 40F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644324360,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(9F, 23F, 11F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644410760,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(10F, 8F, -1F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644497160,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(11F, 11F, 2F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644583560,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(12F, 50F, 40F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644669960,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(13F, 58F, 48F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644756360,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(14F, 67F, 60F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644842760,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(15F, 49F, 40F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1644929160,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(16F, 52F, 41F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1645015560,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(17F, 69F, 64F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),
        DayForecast(
            date = 1645101960,
            sunrise = 1645102800,
            sunset = 1645142400,
            temp = ForecastTemp(17F, 72F, 63F),
            weather = WeatherForecast("Clear", "01d"),
            pressure = 20F,
            humidity = 30
        ),

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MyAdapter(adapterData)



        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/forecast/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        api = retrofit.create(Api::class.java)


    }


    override fun onResume() {
        super.onResume()
        val call: Call<Forecast> = api.getForecast("55128")
        call.enqueue(object : Callback<Forecast> {
            override fun onResponse(
                call: Call<Forecast>,
                response: Response<Forecast>
            ) {
                val forecastConditions = response.body()
                forecastConditions?.let { bindData(it) }

            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {

            }

        })
    }


    private fun bindData(forecast: Forecast) {


        date1.text = forecast.list.firstOrNull()?.date.toString()
        temp1.text = getString(R.string.temp1, forecast.list.first().temp.day)

        high1.text = getString(R.string.high1, forecast.list.first().temp.max)

        low1.text = getString(R.string.low1, forecast.list.first().temp.min)
        sunrise1.text = forecast.list.firstOrNull()?.sunrise.toString()
        sunset1.text = forecast.list.firstOrNull()?.sunset.toString()


        val iconName = forecast.list.firstOrNull()?.weather?.icon

        val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"
        Glide.with(this)
            .load(iconUrl)
            .into(conditionIcon1)



    }



}