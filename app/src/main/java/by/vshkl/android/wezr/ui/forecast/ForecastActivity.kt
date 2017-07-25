package by.vshkl.android.wezr.ui.forecast

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import by.vshkl.android.wezr.R
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.ui.base.BaseActivity
import by.vshkl.android.wezr.util.NavigationUtils
import javax.inject.Inject

class ForecastActivity : BaseActivity(), ForecastView, OnRefreshListener {

    @Inject lateinit var forecastPresenter: ForecastPresenter

    @BindView(R.id.fl_root) lateinit var flRoot: FrameLayout
    @BindView(R.id.sr_refresh) lateinit var srRefresh: SwipeRefreshLayout
    @BindView(R.id.rv_weather_list) lateinit var rvWeatherList: RecyclerView

    override val layout: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)
        activityComponent().inject(this)
        srRefresh.setOnRefreshListener(this)
        forecastPresenter.attachView(this)
        forecastPresenter.getCachedWeatherData()
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
        Snackbar.make(flRoot, R.string.alert_offline, Snackbar.LENGTH_SHORT).show()
    }

    override fun onRefresh() {
        forecastPresenter.fetchWeatherData(26850)
    }
}
