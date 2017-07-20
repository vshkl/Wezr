package by.vshkl.android.wezr.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.models.Weather
import by.vshkl.android.wezr.utils.DateFormatterUtils
import com.bumptech.glide.Glide

class ForecastAdapter(private val weatherList: List<Weather>) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder
            = ForecastViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_weather, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        val weatherItem = weatherList[position]

        Glide.with(holder?.itemView?.context).load(weatherItem.imageUrl).into(holder?.ivWeatherIcon)
        holder?.tvForecastTime?.text = DateFormatterUtils.getReadableDateAndTime(weatherItem.time)
        val temperatureLow = weatherItem.tempLow
        if (temperatureLow > 0) {
            holder?.tvTemperatureLow?.text = "+$temperatureLow°C"
        } else {
            holder?.tvTemperatureLow?.text = "-$temperatureLow°C"
        }
        val temperatureHigh = weatherItem.tempHigh
        if (temperatureHigh > 0) {
            holder?.tvTemperatureHigh?.text = "+$temperatureHigh°C"
        } else {
            holder?.tvTemperatureHigh?.text = "-$temperatureHigh°C"
        }
        holder?.tvWeatherDescription?.text = weatherItem.weatherDescription
        holder?.tvWind?.text = weatherItem.wind
        holder?.tvPressure?.text = weatherItem.pressure
        holder?.tvHumidity?.text = weatherItem.humidity
    }

    override fun getItemCount(): Int = weatherList.size
}