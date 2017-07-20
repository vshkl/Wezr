package by.vshkl.android.wezr.ui.forecast

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.models.Weather
import by.vshkl.android.wezr.ui.base.BaseActivity
import javax.inject.Inject

class ForecasrActivity : BaseActivity(), ForecastView {

    @Inject lateinit var forecastPresenter: ForecastPresenter

    @BindView(R.id.rv_weather_list) lateinit var rvWeatherList: RecyclerView

    override val layout: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        forecastPresenter.attachView(this)
        forecastPresenter.getWeatherData(26850)
    }

    override fun onDestroy() {
        super.onDestroy()
        forecastPresenter.detachView()
    }

    override fun showWeatherList(weatherList: List<Weather>) {

    }
}
