package com.example.weatherapp.forecast

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.DayForecast
import com.example.weatherapp.R
import com.example.weatherapp.databinding.RowDateBinding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

@SuppressLint("NewApi")
class ForecastAdapter(private val data: List<DayForecast>): RecyclerView.Adapter<ForecastAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val timeFormatter = DateTimeFormatter.ofPattern("h:mma")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault())
        private val dateFormatter = DateTimeFormatter.ofPattern("MMM d")
            .withLocale(Locale.getDefault())
            .withZone(ZoneId.systemDefault())

        private val viewBinding = RowDateBinding.bind(itemView)

        fun bind(data: DayForecast) {
            val context  = itemView.context
            viewBinding.date.text = dateFormatter.format(
                Instant.ofEpochSecond(data.date))
            viewBinding.sunrise.text = timeFormatter.format(
                Instant.ofEpochSecond(data.sunrise))
            viewBinding.sunset.text = timeFormatter.format(
                Instant.ofEpochSecond(data.sunset))
            viewBinding.temp.text = context.getString(R.string.temperature, data.temp.day )
            viewBinding.high.text = context.getString(R.string.high, data.temp.max)
            viewBinding.low.text = context.getString(R.string.low, data.temp.min)


            val iconName = data.weather.firstOrNull()?.icon
            val iconUrl = "https://openweathermap.org/img/wn/${iconName}@2x.png"

            Glide.with(itemView.context)
                .load(iconUrl)
                .into(viewBinding.conditionIcon)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(
           R.layout.row_date, parent, false
       )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}