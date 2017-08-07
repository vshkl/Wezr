package by.vshkl.android.wezr.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import by.vshkl.android.wezr.R

class ForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.ivWeatherIcon) lateinit var ivWeatherIcon: ImageView
    @BindView(R.id.tvForecastTime) lateinit var tvForecastTime: TextView
    @BindView(R.id.tvTemperatureLow) lateinit var tvTemperatureLow: TextView
    @BindView(R.id.tvTemperatureHigh) lateinit var tvTemperatureHigh: TextView
    @BindView(R.id.tvWeatherDescription) lateinit var tvWeatherDescription: TextView
    @BindView(R.id.tvWind) lateinit var tvWind: TextView
    @BindView(R.id.tvPressure) lateinit var tvPressure: TextView
    @BindView(R.id.tvHumidity) lateinit var tvHumidity: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}