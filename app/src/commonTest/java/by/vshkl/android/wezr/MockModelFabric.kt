package by.vshkl.android.wezr

import by.vshkl.android.wezr.data.model.Weather
import org.joda.time.DateTime
import java.util.*

object MockModelFabric {

    fun newWeather(timeIncrement: Int): Weather {
        val random = Random()
        return Weather(DateTime.now().withTimeAtStartOfDay().plusHours(timeIncrement), random.nextInt(30),
                random.nextInt(30), randomString(), randomString(), randomString(), randomString(), randomString())
    }

    fun newWeatherList(count: Int): List<Weather> = (0..count - 1).map { newWeather(it) }

    private fun randomString(): String = UUID.randomUUID().toString()
}