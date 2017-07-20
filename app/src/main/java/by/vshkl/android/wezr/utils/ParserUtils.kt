package by.vshkl.android.wezr.utils

import by.vshkl.android.wezr.data.models.Weather
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object ParserUtils {

    val BASE_URL = "http://meteoinfo.by/wrf15/"
    val DIMENSION_WIND = "м/c"
    val DIMENSION_PRESSURE = "мм.рт.ст."

    fun parseHtmlPage(htmlPage: String): List<Weather> {
        val weatherList: MutableList<Weather> = mutableListOf()
        val startTime = DateTime(DateTimeZone.forOffsetHours(3)).withMinuteOfHour(0)
        val weatherElements = Jsoup.parse(htmlPage).select("tr[onmouseover]")

        weatherElements.mapIndexedTo(weatherList) { index, weatherElement ->
            parseWeatherElement(weatherElement, startTime.plusHours(index))
        }

        return weatherList
    }

    internal fun parseWeatherElement(weatherElement: Element, date: DateTime): Weather {
        val temperature = weatherElement.child(1).text().split("..")
        val wind = weatherElement.child(4).text().split(", ")

        return Weather(
                date,
                temperature[0].toInt(),
                temperature[1].toInt(),
                "$BASE_URL${weatherElement.child(2).select("img").attr("src")}",
                weatherElement.child(3).text(),
                "${wind[0]}${DIMENSION_WIND}, ${wind[1]}",
                "${weatherElement.child(5).text().split("[")[1].trimEnd(']')}${DIMENSION_PRESSURE}",
                "${weatherElement.child(6).text()}%"
        )
    }

}