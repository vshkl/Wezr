package by.vshkl.android.wezr.ui.forecast

import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.data.model.AlertType
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.injection.ConfigPersistent
import by.vshkl.android.wezr.ui.base.BasePresenter
import by.vshkl.android.wezr.util.NetworkUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ConfigPersistent
class ForecastPresenter
@Inject constructor(private val dataManager: DataManager, private val networkUtils: NetworkUtils)
    : BasePresenter<ForecastView>() {

    private var forecastView: ForecastView? = null
    private var disposable: Disposable? = null

    override fun attachView(mvpView: ForecastView) {
        forecastView = mvpView
    }

    override fun detachView() {
        forecastView = null
        disposable?.dispose()
    }

    fun getWeatherData(cityCode: Int) {
        disposable = dataManager.getCachedWeatherData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weatherList ->
                    fetchWeatherData(cityCode)
                    if (weatherList.isNotEmpty()) {
                        forecastView?.showWeatherList(weatherList)
                        forecastView?.hideProgressIndicator()
                    }
                }
    }

    fun showRadar() {
        if (networkUtils.isConnected()) {
            forecastView?.showRadar()
        } else {
            forecastView?.showOfflineAlert(AlertType.RADAR)
        }
    }

    fun fetchWeatherData(cityCode: Int) {
        if (networkUtils.isConnected()) {
            disposable = dataManager.getWeatherData(cityCode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { weatherList ->
                        storeWeatherData(weatherList)
                        forecastView?.showWeatherList(weatherList)
                        forecastView?.hideProgressIndicator()
                    }
        } else {
            forecastView?.showOfflineAlert(AlertType.WEATHER_FETCH)
        }
    }

    private fun storeWeatherData(weatherList: List<Weather>) {
        disposable = dataManager.storeWeatherData(weatherList)
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

}
