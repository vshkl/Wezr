package by.vshkl.android.wezr.ui.forecast

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import butterknife.BindView
import butterknife.ButterKnife
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.AlertType
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.util.NavigationUtils
import javax.inject.Inject

class ForecastActivity : BaseActivity(), ForecastView {

    @Inject lateinit var forecastPresenter: ForecastPresenter

    @BindView(R.id.fl_root) lateinit var flRoot: FrameLayout
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
                forecastPresenter.showRadar()
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

    override fun showRadar() {
        NavigationUtils.navigateToRadar(this)
    }

    override fun showOfflineAlert(@AlertType alertType: Int) {
        when (alertType) {
            AlertType.WEATHER_FETCH ->
                Snackbar.make(flRoot, R.string.alert_offline_fetch_weather, Snackbar.LENGTH_SHORT).show()
            AlertType.RADAR ->
                Snackbar.make(flRoot, R.string.alert_offline_radar, Snackbar.LENGTH_SHORT).show()
        }
    }
}
