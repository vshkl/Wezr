package by.vshkl.android.wezr.data

import by.vshkl.android.wezr.data.models.Weather
import by.vshkl.android.wezr.data.remote.WeatherService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager
@Inject constructor(private val weatherService: WeatherService) {

    fun getWeatherData(cityCode: Int): Observable<List<Weather>> {
        return weatherService.getWeatherData(cityCode)
    }
}
