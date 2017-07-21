package by.vshkl.android.wezr.data

import by.vshkl.android.wezr.Application
import by.vshkl.android.wezr.data.mapper.WeatherEntityMapper
import by.vshkl.android.wezr.data.model.Weather
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

    fun storeWeatherData(weatherList: List<Weather>): Observable<Void> = Observable.create {
        Application.database?.weatherDao()?.insertAll(WeatherEntityMapper.transform(weatherList))
    }
}
