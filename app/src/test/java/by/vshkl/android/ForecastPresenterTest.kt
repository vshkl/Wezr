package by.vshkl.android

import by.vshkl.android.wezr.MockModelFabric
import by.vshkl.android.wezr.data.DataManager
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.ui.forecast.ForecastPresenter
import by.vshkl.android.wezr.ui.forecast.ForecastView
import by.vshkl.android.wezr.util.NetworkUtils
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ForecastPresenterTest {

    @Mock lateinit var forecastView: ForecastView
    @Mock lateinit var dataManager: DataManager
    @Mock lateinit var networkUtils: NetworkUtils
    private lateinit var forecastPresenter: ForecastPresenter

    @Before
    fun setUp() {
        forecastPresenter = ForecastPresenter(dataManager, networkUtils)
        forecastPresenter.attachView(forecastView)
    }

    @After
    fun rearDown() {
        forecastPresenter.detachView()
    }

//    @Test
//    fun getCachedWeatherData() {
//        val weatherList = MockModelFabric.newWeatherList(10)
//        `when`(dataManager.getCachedWeatherData())
//                .thenReturn(Single.just(weatherList))
//
//        forecastPresenter.getCachedWeatherData()
//        verify(forecastView, never()).showWeatherList(ArgumentMatchers.anyList<Weather>())
//    }
}