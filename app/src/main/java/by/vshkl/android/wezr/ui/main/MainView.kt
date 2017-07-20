package by.vshkl.android.wezr.ui.main

import by.vshkl.android.wezr.data.models.Weather
import by.vshkl.android.wezr.ui.base.MvpView

interface MainView : MvpView {
    fun showWeatherList(weatherList: List<Weather>)
}
