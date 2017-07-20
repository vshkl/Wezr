package by.vshkl.android.wezr.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import by.vshkl.android.wezr.R

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.iv_weather_icon) lateinit var ivWeatherIcon: ImageView
    @BindView(R.id.tv_forecast_time) lateinit var tvForecastTime: TextView
    @BindView(R.id.tv_temperature_low) lateinit var tvTemperatureLow: TextView
    @BindView(R.id.tv_temperature_high) lateinit var tvTemperatureHigh: TextView
    @BindView(R.id.tv_weather_description) lateinit var tvWeatherDescription: TextView
    @BindView(R.id.tv_wind) lateinit var tvWind: TextView
    @BindView(R.id.tv_pressure) lateinit var tvPressure: TextView
    @BindView(R.id.tv_humidity) lateinit var tvHumidity: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}