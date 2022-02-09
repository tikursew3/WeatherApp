package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Forecast : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    //private val forececastTemp = ForecastTemp(1.0F, 2.0F, 3.0F)


    private val adapterData = listOf<Data>(

        Data( date = 1643805960,  sunrise = 1644994800, sunset = 1645034400,
        temp = ForecastTemp(3F, 40F, 60F)),

        Data( date= 1643892360, sunrise = 1644994800, sunset = 1645034400,
            temp = ForecastTemp(4F, 42F, 55F)),
       Data( date= 1643978760,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(5F, 23F, 32F)),
       Data( date= 1644065160,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(6F, 34F, 44F)),
       Data( date= 1644151560,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(7F, 41F, 60F)),
       Data( date= 1644237960,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(8F, 40F, 61F)),
        Data( date= 1644324360,  sunrise = 1644994800, sunset = 1645034400,
            temp = ForecastTemp(9F, 11F, 23F)),
        Data( date= 1644410760,  sunrise = 1644994800, sunset = 1645034400,
            temp = ForecastTemp(10F, -1F, 8F)),
       Data( date= 1644497160,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(11F, 2F, 11F)),
       Data( date= 1644583560,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(12F, 40F, 50F)),
       Data( date= 1644669960,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(13F, 48F, 58F)),
       Data( date= 1644756360,  sunrise = 1644994800, sunset = 1645034400,
           temp = ForecastTemp(14F, 60F, 67F)),
        Data( date= 1644842760,  sunrise = 1644994800, sunset = 1645034400,
            temp = ForecastTemp(15F, 40F, 49F)),
        Data( date= 1644929160,  sunrise = 1644994800, sunset = 1645034400,
            temp = ForecastTemp(16F, 40F, 52F)),
        Data( date= 1645015560,  sunrise = 1644994800, sunset = 1645034400,
            temp = ForecastTemp(17F, 64F, 69F)),




    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = MyAdapter(adapterData)


    }
}