package by.vshkl.android.wezr.data.mapper

import by.vshkl.android.wezr.data.entity.WeatherEntity
import by.vshkl.android.wezr.data.model.Weather

object WeatherEntityMapper {

    fun transform(weather: Weather): WeatherEntity = WeatherEntity(
            weather.time.millis,
            weather.tempLow,
            weather.tempHigh,
            weather.imageUrl,
            weather.weatherDescription,
            weather.wind,
            weather.pressure,
            weather.humidity
    )

    fun transform(weatherList: List<Weather>): List<WeatherEntity> = weatherList.map { transform(it) }
}