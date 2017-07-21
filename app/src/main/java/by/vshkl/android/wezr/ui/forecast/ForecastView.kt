package by.vshkl.android.wezr.ui.forecast

import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.ui.base.MvpView

interface ForecastView : MvpView {

    fun showProgressIndicator()

    fun hideProgressIndicator()

    fun showWeatherList(weatherList: List<Weather>)

    fun showRadar()
}
