package by.vshkl.android.wezr.util

import by.vshkl.android.wezr.data.model.Weather
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.jsoup.Jsoup
import org.jsoup.nodes.Element

object ParserUtils {

    val BASE_URL = "http://meteoinfo.by/wrf15/"
    val DIMENSION_WIND = " м/c"
    val DIMENSION_PRESSURE = " мм рт.ст"

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
        var wind = weatherElement.child(4).text()
        if (wind.last().isDigit()) {
            wind += DIMENSION_WIND
        }
        if (wind[wind.indexOf(",", 0, false) - 1].isDigit()) {
            wind = wind.replaceFirst(",", "$DIMENSION_WIND,")
        }

        return Weather(
                date,
                temperature[0].toInt(),
                temperature[1].toInt(),
                "$BASE_URL${weatherElement.child(2).select("img").attr("src")}",
                weatherElement.child(3).text(),
                wind,
                "${weatherElement.child(5).text().split("[")[1].trimEnd(']')}$DIMENSION_PRESSURE",
                "${weatherElement.child(6).text()}%"
        )
    }

}