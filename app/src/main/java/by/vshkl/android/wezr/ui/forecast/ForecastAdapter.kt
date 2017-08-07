package by.vshkl.android.wezr.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.Weather

class ForecastAdapter(private val weatherList: List<Weather>) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastViewHolder
            = ForecastViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_weather, parent, false))

    override fun onBindViewHolder(holder: ForecastViewHolder?, position: Int) {
        holder?.bindWeather(weatherList[position])
    }

    override fun getItemCount(): Int = weatherList.size
}