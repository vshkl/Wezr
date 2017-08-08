package by.vshkl.android.wezr.ui.forecast

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.util.NavigationUtils
import kotlinx.android.synthetic.main.activity_forecast.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class ForecastActivity : BaseActivity(), ForecastView, OnRefreshListener {

    @Inject lateinit var forecastPresenter: ForecastPresenter

    override val layout: Int get() = R.layout.activity_forecast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        srRefresh.setOnRefreshListener(this)
        forecastPresenter.attachView(this)
        forecastPresenter.getCachedWeatherData()
    }

    override fun onStart() {
        super.onStart()
        forecastPresenter.getCachedCities()
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
        srRefresh.isRefreshing = true
    }

    override fun hideProgressIndicator() {
        srRefresh.isRefreshing = false
    }

    override fun showWeatherList(weatherList: List<Weather>) {
        rvWeatherList.setHasFixedSize(true)
        rvWeatherList.layoutManager = LinearLayoutManager(this)
        rvWeatherList.adapter = ForecastAdapter(weatherList)
    }

    override fun showRadar() {
        NavigationUtils.navigateToRadar(this)
    }

    override fun showOfflineAlert() {
        snackbar(srRefresh, R.string.alert_offline)
    }

    override fun onRefresh() {
        forecastPresenter.fetchWeatherData(26850)
    }
}
