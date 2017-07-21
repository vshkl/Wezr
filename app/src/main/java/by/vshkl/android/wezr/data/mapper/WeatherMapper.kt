package by.vshkl.android.wezr.data.mapper

import by.vshkl.android.wezr.data.entity.WeatherEntity
import by.vshkl.android.wezr.data.model.Weather
import org.joda.time.DateTime

object WeatherMapper {

    fun transform(weatherEntity: WeatherEntity): Weather = Weather(
            DateTime(weatherEntity.time),
            weatherEntity.tempLow,
            weatherEntity.tempHigh,
            weatherEntity.imageUrl,
            weatherEntity.weatherDescription,
            weatherEntity.wind,
            weatherEntity.pressure,
            weatherEntity.humidity
    )

    fun transform(weatherEntityList: List<WeatherEntity>): List<Weather> = weatherEntityList.map { transform(it) }
}