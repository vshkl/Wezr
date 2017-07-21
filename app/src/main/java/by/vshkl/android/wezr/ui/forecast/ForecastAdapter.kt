package by.vshkl.android.wezr.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.util.DateFormatterUtils
import com.bumptech.glide.Glide

class ForecastAdapter(private val weatherList: List<Weather>) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder
            = ForecastViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_weather, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        with(weatherList[position]) {
            Glide.with(holder?.itemView?.context).load(imageUrl).into(holder?.ivWeatherIcon)
            holder?.tvForecastTime?.text = DateFormatterUtils.getReadableDateAndTime(time)
            holder?.tvTemperatureLow?.text = holder?.itemView?.context?.getString(R.string.template_temp_celsius, tempLow)
            holder?.tvTemperatureHigh?.text = holder?.itemView?.context?.getString(R.string.template_temp_celsius, tempHigh)
            holder?.tvWeatherDescription?.text = weatherDescription
            holder?.tvWind?.text = wind
            holder?.tvPressure?.text = pressure
            holder?.tvHumidity?.text = humidity
        }
    }

    override fun getItemCount(): Int = weatherList.size
}