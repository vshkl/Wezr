package by.vshkl.android.wezr.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.View
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.util.DateTImeUtils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_weather.view.*

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindWeather(weather: Weather) = with(weather) {
        Glide.with(itemView.context).load(imageUrl).into(itemView.ivWeatherIcon)
        itemView.tvForecastTime.text = DateTImeUtils.getReadableDateAndTime(time)
        itemView.tvTemperatureLow.text = itemView.context.getString(R.string.template_temp_celsius, tempLow)
        itemView.tvTemperatureHigh.text = itemView.context.getString(R.string.template_temp_celsius, tempHigh)
        itemView.tvWeatherDescription.text = weatherDescription
        itemView.tvWind.text = wind
        itemView.tvPressure.text = pressure
        itemView.tvHumidity.text = humidity
    }
}