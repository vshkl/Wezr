package by.vshkl.android.wezr.data

import by.vshkl.android.wezr.Application
import by.vshkl.android.wezr.data.mapper.WeatherEntityMapper
import by.vshkl.android.wezr.data.mapper.WeatherMapper
import by.vshkl.android.wezr.data.model.Weather
import by.vshkl.android.wezr.data.remote.WeatherService
import by.vshkl.android.wezr.util.DateTImeUtils
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager
@Inject constructor(private val weatherService: WeatherService) {

    fun getWeatherData(cityCode: Int): Single<List<Weather>> = weatherService.getWeatherData(cityCode)

    fun getCachedWeatherData(): Single<List<Weather>> = Single.create {
        it.onSuccess(WeatherMapper.transform(Application.database.weatherDao().getAll())
                .sortedBy { it.time }
                .filter { DateTImeUtils.isAfterCurrentHour(it.time) })
    }

    fun storeWeatherData(weatherList: List<Weather>): Single<Unit> = Single.create {
        Application.database.weatherDao().deleteAll()
        it.onSuccess(Application.database.weatherDao().insertAll(WeatherEntityMapper.transform(weatherList)))
    }
}
