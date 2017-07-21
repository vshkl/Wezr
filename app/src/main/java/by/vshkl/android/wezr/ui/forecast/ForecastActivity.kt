package by.vshkl.android.wezr.ui.forecast

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.ui.base.BaseActivity
import javax.inject.Inject


class ForecastActivity : BaseActivity(), ForecastView {

    @Inject lateinit var forecastPresenter: ForecastPresenter

    @BindView(R.id.rv_weather_list) lateinit var rvWeatherList: RecyclerView
    @BindView(R.id.pb_progress) lateinit var pbProgressIndicator: ProgressBar

    override val layout: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        activityComponent().inject(this)
        forecastPresenter.attachView(this)
        forecastPresenter.getWeatherData(26850)
    }

    override fun onDestroy() {
        super.onDestroy()
        forecastPresenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_forecast, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_show_radar -> {
                Toast.makeText(this, "Show radar", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun showProgressIndicator() {
        rvWeatherList.visibility = View.GONE
        pbProgressIndicator.visibility = View.VISIBLE
    }

    override fun hideProgressIndicator() {
        pbProgressIndicator.visibility = View.GONE
        rvWeatherList.visibility = View.VISIBLE
    }

    override fun showWeatherList(weatherList: List<Weather>) {
        rvWeatherList.setHasFixedSize(true)
        rvWeatherList.layoutManager = LinearLayoutManager(this)
        rvWeatherList.adapter = ForecastAdapter(weatherList)
    }
}
