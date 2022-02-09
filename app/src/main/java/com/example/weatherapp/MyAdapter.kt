package com.example.weatherapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

//extend the RecyclerView.Adapter class
class MyAdapter(private val data: List<Data>):RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    //extend the RecyclerView.ViewHolder class
     class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val dateView: TextView = view.findViewById(R.id.date)
         private val sunriseView: TextView = view.findViewById(R.id.sunrise)
         private val sunsetView: TextView = view.findViewById(R.id.sunset)


         private val tempViewHigh: TextView = view.findViewById(R.id.high)
         private val tempViewLow: TextView = view.findViewById(R.id.low)
        private val tempView: TextView = view.findViewById(R.id.temp)

        @SuppressLint("NewApi")
        fun bind(data: Data) {

            val instant = Instant.ofEpochSecond(data.date)
            val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofPattern("MMM dd")
            dateView.text = formatter.format(dateTime)


            tempView.text = "Temp " + data.temp.day.toFloat().toString()

            tempViewHigh.text = "High " + data.temp.max.toInt().toString()

            tempViewLow.text = "Low " +  data.temp.min.toInt().toString()


            val sunriseInstant = Instant.ofEpochSecond(data.sunrise)
            val sunriseTime = LocalDateTime.ofInstant(sunriseInstant, ZoneId.systemDefault())
            val sunriseFormatter = DateTimeFormatter.ofPattern("HH MM a")

            sunriseView.text = "sunrise "+ sunriseFormatter.format(sunriseTime)

            val sunsetInstant = Instant.ofEpochSecond(data.sunset)
            val sunsetTime = LocalDateTime.ofInstant(sunsetInstant, ZoneId.systemDefault())
            val sunsetFormatter = DateTimeFormatter.ofPattern("HH MM a")

            sunsetView.text = "sunset "+ sunsetFormatter.format(sunsetTime)







        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_date, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size
}